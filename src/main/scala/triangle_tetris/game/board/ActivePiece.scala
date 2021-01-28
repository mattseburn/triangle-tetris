package triangle_tetris.game.board

import triangle_tetris.game.pieces.Piece
import triangle_tetris.game.board.Direction._

import scala.util.Random

case class ActivePiece(piece: Piece,
                       location: CellIndex = CellIndex(0, 0, 0),
                       rotation: Int = 0) {

  def rotate(r: Int): ActivePiece =
    ActivePiece(piece, location, rotation + r)

  def move(direction: Direction): ActivePiece =
    ActivePiece(piece, location + CellIndex(direction))

  def cellIndexes: List[CellIndex] =
    piece.layout.map(applyLocation)

  def cells: Map[CellIndex, Cell] =
    cellIndexes.map((_, Cell(Some(piece.color)))).toMap

  private def applyLocation(delta: CellIndex): CellIndex =
    location + delta
}

object ActivePiece {
  def apply(grid: Grid): ActivePiece =
    ActivePiece(Piece(), grid.columnHeads(Random.nextInt(grid.columnHeads.size))) match {
      case piece: ActivePiece =>
        if (grid.contains(piece.cellIndexes)) { piece }
        else if (grid.contains(piece.move(Right).cellIndexes)) { piece.move(Right) }
        else if (grid.contains(piece.move(Left).cellIndexes)) { piece.move(Left) }
        else if (grid.contains(piece.move(Down).cellIndexes)) { piece.move(Down) }
        else { apply(grid) }
  }
}
