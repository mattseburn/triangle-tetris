package triangle_tetris.game.board

import triangle_tetris.game.pieces.Piece
import triangle_tetris.game.board.movement.Direction._
import triangle_tetris.game.board.grid.{Cell, CellIndex, Grid}
import triangle_tetris.game.board.movement.{Direction, Rotation, RotationalDirection}

import scala.util.Random

case class ActivePiece(piece: Piece,
                       location: CellIndex = CellIndex(0, 0, 0),
                       rotation: Rotation = Rotation()) {

  def rotate(rotationalDirection: RotationalDirection): ActivePiece =
    this.copy(rotation = rotation.update(rotationalDirection))

  def move(direction: Direction): ActivePiece =
    this.copy(location = location + CellIndex(direction))

  def cellIndexes: List[CellIndex] =
    piece.layout
      .map(rotation.rotateCell)
      .map(_ + location)

  def cells: Map[CellIndex, Cell] =
    cellIndexes.map((_, Cell(Some(piece.color)))).toMap
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

/*

  - need to know adjacent orientation
  - orientation should have deltas
  - and probably next / prev
  - interface isn't great so far

  - have:
    - active piece's orientation
    - rotation direction
  - need:
    - rotation delta for each cell index

  - maybe represent rotations
    - (Orientation, RotationalDirection) ?
    - rotation case objects
      - maps orientation & direction to the correct axes definitions
  - create separate case objects for axes and +/-
    - results returned are tuples?
      - no rotations

 */

