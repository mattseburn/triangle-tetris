package triangle_tetris.game.board

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import triangle_tetris.game.board.grid.CellIndex
import triangle_tetris.game.board.movement.RotationalDirection._
import triangle_tetris.game.board.movement.Direction._
import triangle_tetris.game.pieces.Piece

class ActivePieceSpec extends AnyWordSpec {
  "rotate" should {
    "return a new active piece with the updated rotation" in {
      val a = ActivePiece(Piece())

      a.rotate(Clockwise) shouldEqual ActivePiece(a.piece, a.location, a.rotation.update(Clockwise))
    }
  }

  "move" should {
    "return a new active piece with the updated location" in {
      val a = ActivePiece(Piece())

      a.move(Down) shouldEqual ActivePiece(a.piece, a.location + CellIndex(Down), a.rotation)
    }
  }

  "cellIndexes" should {
    "return the piece layout with the transformations applied" in {
      val a = ActivePiece(Piece())

      a
        .rotate(Clockwise)
        .move(Down)
        .cellIndexes shouldEqual a.piece.layout
          .map(a.rotation.update(Clockwise).rotateCell)
          .map(_ + a.location + CellIndex(Down))
    }
  }
}
