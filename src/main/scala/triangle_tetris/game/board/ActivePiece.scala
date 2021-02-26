package triangle_tetris.game.board

import triangle_tetris.game.pieces.{Color, Piece, PieceLayout, PieceOrientation}
import triangle_tetris.game.board.movement.Direction._
import triangle_tetris.game.board.grid.{Cell, CellIndex, Grid}
import triangle_tetris.game.board.movement.{Direction, Rotation, RotationalDirection}

import scala.util.Random

case class ActivePiece(piece: Piece,
                       pieceOrientation: PieceOrientation,
                       location: CellIndex = CellIndex(0, 0, 0),
                       rotation: Rotation = Rotation()) {

  def rotate(rotationalDirection: RotationalDirection): ActivePiece =
    this.copy(rotation = rotation.update(rotationalDirection))

  def move(direction: Direction): ActivePiece =
    this.copy(location = location + CellIndex(direction))

  def cellIndexes: List[CellIndex] =
    PieceLayout(piece, pieceOrientation).cells
      .map(rotation.rotateCell)
      .map(_ + location)

  def cells: Map[CellIndex, Cell] =
    cellIndexes.map((_, Cell(Some(Color(piece))))).toMap

  override def toString: String =
    s"[ActivePiece | $piece | $pieceOrientation | $location | $rotation]"
}

object ActivePiece {
  def apply(grid: Grid): ActivePiece =
    ActivePiece(Piece(), PieceOrientation(), grid.columnHeads(Random.nextInt(grid.columnHeads.size))) match {
      case piece: ActivePiece =>
        if (grid.contains(piece.cellIndexes)) { piece }
        else if (grid.contains(piece.move(Right).cellIndexes)) { piece.move(Right) }
        else if (grid.contains(piece.move(Left).cellIndexes)) { piece.move(Left) }
        else if (grid.contains(piece.move(Down).cellIndexes)) { piece.move(Down) }
        else { apply(grid) }
    }
}
