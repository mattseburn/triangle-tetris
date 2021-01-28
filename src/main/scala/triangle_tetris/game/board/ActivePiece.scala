package triangle_tetris.game.board

import triangle_tetris.game.pieces.Piece

import scala.util.Random

case class ActivePiece(piece: Piece,
                       location: CellIndex = CellIndex(0, 0, 0),
                       rotation: Int = 0) {

  def rotate(r: Int): ActivePiece =
    ActivePiece(piece, location, rotation + r)

  def moveDown: ActivePiece =
    ActivePiece(piece, location + CellIndex(0, -1, -1))

  def moveLeft: ActivePiece =
    ActivePiece(piece, location + CellIndex(location.i - 1, location.j - 1, location.k))

  def moveRight: ActivePiece =
    ActivePiece(piece, location + CellIndex(location.i + 1, location.j, location.k - 1))

  def cellIndexes: List[CellIndex] =
    piece.layout.map(applyLocation)

  def cells: Map[CellIndex, Cell] =
    cellIndexes.map((_, Cell(Some(piece.color)))).toMap

  private def applyLocation(delta: CellIndex): CellIndex =
    location + delta
}

object ActivePiece {
  def apply(grid: Grid): ActivePiece =
    ActivePiece(Piece(), grid.columnHeads(Random.nextInt(grid.columnHeads.length))) match {
      case piece: ActivePiece =>
        if (grid.contains(piece.cellIndexes)) { piece }
        else if (grid.contains(piece.moveRight.cellIndexes)) { piece.moveRight }
        else if (grid.contains(piece.moveLeft.cellIndexes)) { piece.moveLeft }
        else if (grid.contains(piece.moveDown.cellIndexes)) { piece.moveDown }
        else { apply(grid) }
    }
}
