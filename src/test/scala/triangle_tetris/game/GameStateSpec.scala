package triangle_tetris.game

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import triangle_tetris.game.board.ActivePiece
import triangle_tetris.game.board.grid.{CellIndex, Grid}
import triangle_tetris.game.board.movement.Direction._
import triangle_tetris.game.board.movement.RotationalDirection.Clockwise
import triangle_tetris.game.pieces.{Piece, PieceOrientation}

class GameStateSpec extends AnyWordSpec {
  private val grid = Grid(6, 6)

  "togglePause" should {
    "toggle the paused state" in {
      val gameState = GameState(grid)

      gameState.paused shouldBe false
      gameState.togglePause.paused shouldBe true
    }
  }

  "move" when {
    "the requested movement is possible" should {
      "move the active piece" in {
        val activePiece = ActivePiece(Piece(), PieceOrientation(), CellIndex(0, 0, 0))

        GameState(grid, activePiece)
          .move(Down)
          .activePiece shouldEqual activePiece.move(Down)
      }
    }

    "the requested movement is not possible, but the piece can still move down" should {
      "do nothing" in {
        val activePiece = ActivePiece(Piece(), PieceOrientation(), CellIndex(-2, 2, 4))

        GameState(grid, activePiece)
          .move(Left)
          .activePiece shouldEqual activePiece
      }
    }

    "the piece cannot move at all" should {
      "create a new active piece" in {
        val activePiece = ActivePiece(Piece(), PieceOrientation(), CellIndex(0, -3, -3))

        GameState(grid, activePiece)
          .move(Left)
          .activePiece should not equal activePiece
      }
    }
  }

  "rotate" should {
    "rotate the active piece" in {
      val activePiece = ActivePiece(Piece(), PieceOrientation(), CellIndex(0, 0, 0))

      GameState(grid, activePiece)
        .rotate(Clockwise)
        .activePiece shouldEqual activePiece.rotate(Clockwise)
    }
  }

  "setTimestamp" should {
    "update the last timestamp" in {
      GameState(grid)
        .setTimestamp(1000L)
        .lastTimestamp shouldEqual 1000L
    }
  }
}
