package co.mglvl

import java.util.Date
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.persistence.{RecoveryCompleted, PersistentActor}
import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag
import scala.util.{Failure, Success}

/**
 * Created by mglvl on 28/05/15.
 */
trait Event

sealed trait ListenerEvent[E<:Event]
case class EventReceived[E<:Event](event: E, date: Date) extends ListenerEvent[E]
case class EventSuccessfullyProcessed[E<:Event](event: E, date: Date) extends ListenerEvent[E]
case class EventProcessingFailed[E<:Event](event: E, cause: Throwable, date: Date) extends ListenerEvent[E]

abstract class EventListener[E<:Event](val id: String) {
  def processEvent(event: E): Future[Unit]
}

class PersistentListener[E <: Event : ClassTag](eventListener: EventListener[E]) extends PersistentActor {

  implicit val executionContext = context.system.dispatcher

  override def persistenceId: String = eventListener.id

  val pendingEvents = mutable.Set[E]()
  val failedEvents = mutable.Set[EventProcessingFailed[E]]()

  case class ProcessSuccess(event: E)
  case class ProcessFailure(event: E, cause: Throwable)

  override def receiveCommand: Receive = {
    case event: E =>
      persist(EventReceived(event, new Date())) { _ =>
        pendingEvents.add(event)
        eventListener.processEvent(event).onComplete {
          case Success(_) => self ! ProcessSuccess(event)
          case Failure(cause) => self ! ProcessFailure(event, cause)
        }
      }
    case ProcessSuccess(event) =>
      persist(EventSuccessfullyProcessed(event, new Date())) { _ =>
        pendingEvents.remove(event)
      }
    case ProcessFailure(event, cause) =>
      val failure = EventProcessingFailed(event, cause, new Date())
      persist(failure) { _ =>
        failedEvents.add(failure)
        pendingEvents.remove(event)
      }
    case RecoveryCompleted =>
      pendingEvents.foreach(e => self ! e)
  }

  override def receiveRecover: Receive = {
    case listenerEvent: ListenerEvent[E] => recoverEvent(listenerEvent)
  }

  def recoverEvent(listenerEvent: ListenerEvent[E]): Unit = listenerEvent match {
    case EventReceived(event,_) => pendingEvents.add(event)
    case EventSuccessfullyProcessed(event,_) => pendingEvents.remove(event)
    case failure: EventProcessingFailed[E] =>
      failedEvents.add(failure)
      pendingEvents.remove(failure.event)
  }

}

object PersistentListener {

  private def props[E<:Event:ClassTag](eventListener: EventListener[E]): Props = Props(new PersistentListener[E](eventListener))

  def createActor[E<: Event : ClassTag](eventListener: EventListener[E])(implicit actorSystem: ActorSystem): ActorRef = {
    val actorRef = actorSystem.actorOf(props(eventListener))
    actorSystem.eventStream.subscribe(actorRef, implicitly[ClassTag[E]].getClass)
    actorRef
  }

}