package triangle_tetris.game.pieces

import triangle_tetris.game.Color
import triangle_tetris.game.board.CellIndex

case class LinePiece() extends Piece(LinePiece.layout, Color.Green)

object LinePiece {
  val layout: List[CellIndex] = List(
    CellIndex(0, 1, 1),
    CellIndex(0, 0, 1),
    CellIndex(0, 0, 0),
    CellIndex(0, -1, 0),
    CellIndex(0, -1, -1),
    CellIndex(0, -2, -1))
}
