package triangle_tetris.game.pieces

import triangle_tetris.game.Color
import triangle_tetris.game.board.CellIndex

import scala.util.Random

abstract class Piece(val layout: List[CellIndex],
                     val color: Color)

object Piece {
  def apply(): Piece = Random.nextInt(3) match {
    case 0 => HexagonPiece()
    case 1 => HourGlassPiece()
    case 2 => LinePiece()
  }
}
