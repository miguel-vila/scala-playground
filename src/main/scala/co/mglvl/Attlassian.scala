package co.mglvl

import java.io.PrintWriter

/**
 * Created by mglvl on 24/06/15.
 */

import java.io.PrintWriter
object Solution {
  class LinkedListNode(node_value: String) {
    var value: String = node_value
    var next: LinkedListNode = null
  }

  def _insert_node_into_singlylinkedlist(head: LinkedListNode, value: String): LinkedListNode = {
    var head_copy:LinkedListNode = head
    if (head_copy == null) {
      head_copy = new LinkedListNode(value);
    }
    else {
      var end:LinkedListNode = head;
      while (end.next != null) {
        end = end.next;
      }
      var node:LinkedListNode = new LinkedListNode(value);
      end.next = node;
    }
    return head_copy;
  }

  def fold(list: LinkedListNode): String = {
    def loop(list: LinkedListNode, acc: String): String = {
      if(list.next == null){
        acc + list.value
      } else {
        loop(list.next, acc + list.value)
      }
    }
    loop(list, "")
  }

  /*
  def find(list: LinkedListNode, sublist: LinkedListNode): Int = {
    var currentList = list
    var currentSublist = sublist
    var index = 0
    while(true){
      if(currentList.value == currentSublist.value){
        if(currentList.next == currentSublist.next && currentList.next == null)
          return index
        else{

        }
      }
    }
  }
  */

}


/*
object Solution2  {

  /*
  println("1" +" -> " + look("1"))
  println("11" +" -> " + look("11"))
  println("21" +" -> " + look("21"))
  println("1211" +" -> " + look("1211"))
  println("111221" +" -> " + look("111221"))

  println(LookAndSay("11",2))
  */

  def main(args: Array[String]):Unit = {
    val _start = scala.io.StdIn.readLine()


    var _n:Int = scala.io.StdIn.readInt()


    val res =         LookAndSay(_start, _n)

    println(res)



  }

  def LookAndSay(start: String, n: Int): String = {
    def look(start: String): String = {
      var current = start(0)
      var howMany = 1
      var answer = ""
      for {
        i <- (1 to start.length - 1)
      } {
        val c = start(i)
        if (c == current) {
          howMany = howMany + 1
        } else {
          answer = s"$answer$howMany$current"
          howMany = 1
          current = c
        }
      }
      s"$answer$howMany$current"
    }
    if(n == 0) {
      start
    } else {
      LookAndSay(look(start), n-1)
    }
  }

}

*/

/*
object Solution3 extends App {


  val attlasian = List('0','a','t','l','s','i','n')
  val base = attlasian.length

  def convert(in: Long): String = {
    if(in<base)
      attlasian(in.toInt).toString
    else {
      val r = in % base
      convert(in / base)+attlasian(r.toInt)
    }
  }

  println(convert(7))
  println(convert(10))
  println(convert(234))
  println(convert(49))
  println(convert(50))

}

*/

/*
object Solution4 extends App{
  class State(var robotPos: Int = 0, var hasBlock: Boolean = false, val blocks: Array[Int] = Array(0,0,0,0,0,0,0,0,0,0)){
    def execute(order: Char): Unit = {
      order match {
        case 'P' =>
          this.hasBlock = true
          this.robotPos = 0
        case 'M' =>
          this.robotPos = Math.min(this.robotPos + 1 , 9)
        case 'L' =>
          if(hasBlock && this.blocks(robotPos)<15) {
            this.blocks.update(robotPos, this.blocks(robotPos) + 1)
            this.hasBlock = false
          }
        case _ => //nothing!
      }
    }
  }

  def compute(instructions: String): String = {
    val chars = Array('A','B','C','D','E','F')
    val state = new State()
    instructions.foreach(state.execute)
    var response = ""
    state.blocks.foreach { n =>
      if(n<10)
        response = response + n
      else
        response = response + chars(n-10)
    }
    response
  }

  println(compute("PMLPMMMLPMLPMML"))
  println(compute("PLPLPLPLPLPLPLPLPLPL"))

}
*/

/*
object Solution5 extends App {
  def validChar(c: Char) = c >= ' ' && c <= '~'

  def validate(input: String): String = {
    val rows = input.split("~n")

  }

  println(validate("|name|address|~n|Pattrick|pattric@test.com|pat@test.com|~n|Annie||annie@test.com|~n"))
}
*/