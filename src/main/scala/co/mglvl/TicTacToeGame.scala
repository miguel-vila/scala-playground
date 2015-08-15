package co.mglvl

/**
 * Created by mglvl on 9/06/15.
 */
/*
sealed trait Player {
  def other: Player
}
object PlayerX extends Player {
  def other = PlayerO
}
object PlayerO extends Player {
  def other = PlayerX
}

sealed trait TicTacToeGame {
  def playerAt(x: Int, y: Int): Option[Player]

}

case class NotStartedGame(player: Player) extends TicTacToeGame {
  def move(x: Int, y: Int): InPlayGame = {

  }
  def playerAt(x: Int, y: Int): Option[Player] = None
}

case class InPlayGame(player: Player, board: Board) extends TicTacToeGame {
  def move(player: Player): TicTacToeGame
  def playerAt(x: Int, y: Int): Option[Player] = board(x)(y)
}

trait FinishedGame extends TicTacToeGame

trait WonGame extends FinishedGame {
  def whoWon: Player
}

trait DrawGame extends FinishedGame

case class Board(content: Vector[Vector[Option[Player]]]) extends AnyVal {

  def setPlayerInBoard(x: Int, y: Int, player: Player): Board = {
    if(content(x)(y).isDefined)
      throw new Exception("Already occupied tile")
    else
      Board(content.updated(x, content(x).updated(y,Some(player)) ))
  }

}
*/