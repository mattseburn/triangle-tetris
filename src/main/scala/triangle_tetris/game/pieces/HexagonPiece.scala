package triangle_tetris.game.pieces

import triangle_tetris.game.Color
import triangle_tetris.game.board.CellIndex

case class HexagonPiece()
  extends Piece(HexagonPiece.layout, Color.Red)

object HexagonPiece {
  val layout: List[CellIndex] = List(
    CellIndex(0, 0, 0),
    CellIndex(1, 0, 0),
    CellIndex(1, 0, -1),
    CellIndex(1, -1, -1),
    CellIndex(0, -1, -1),
    CellIndex(0, -1, 0))
}
