package triangle_tetris.game.board

import triangle_tetris.game.pieces.Piece

case class ActivePiece(piece: Piece, location: CellIndex = CellIndex(0, 0, 0)) {

  def cellIndexes: List[CellIndex] =
    piece.layout.map(_ + location)

  def cells: Map[CellIndex, Cell] =
    cellIndexes.map((_, Cell(Some(piece.color)))).toMap
}
