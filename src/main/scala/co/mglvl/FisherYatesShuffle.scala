package co.mglvl

import com.nicta.rng.Rng
import scalaz._
import Scalaz._

object FisherYatesShuffle {

  def randomJ(i: Int): Rng[Int] = Rng.chooseint(0,i)

  type Exchange = (Int,Int)

  def applyExchange[A](exchange: Exchange)(l: Vector[A]): Vector[A] = {
    val (i,j) = exchange
    val vi = l(i)
    l.updated(i,l(j)).updated(j,vi)
  }

  def stApplyExchange[A](exchange: Exchange): State[Vector[A], Unit] = State.modify(applyExchange(exchange))

  def shuffle[A](l: Vector[A]): Rng[Vector[A]] = {
    val rngExchanges: Rng[Vector[Exchange]] = (l.length - 1 to 1 by -1).toVector.traverseU { i =>
      for {
        j <- randomJ(i)
      } yield (i, j)
    }

    for {
      exchanges <- rngExchanges
    } yield exchanges.traverseU(stApplyExchange[A]).exec(l)
  }

}
