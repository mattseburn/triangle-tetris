package triangle_tetris.game

import triangle_tetris.game.board.{ActivePiece, Cell, CellIndex, Grid}
import triangle_tetris.game.pieces.Piece

class GameStateManager(width: Int,
                       height: Int,
                       frameRate: Double) {

  private def newPiece =
    ActivePiece(Piece())
  private var gameState =
    GameState(Grid(width, height))

  private def setState(newState: GameState): Unit =
    gameState = newState

  private def removePieceFromGrid(): Unit =
    setState(gameState.copy(
      grid = gameState.grid
        .update(gameState.activePiece.cellIndexes.map((_, Cell(None))).toMap)))

  private def placePieceOnGrid(): Unit =
    setState(gameState.copy(grid = gameState.grid.update(gameState.activePiece.cells)))

  private def setNewPiece(): Unit =
    setState(gameState.copy(activePiece = newPiece))

  private def moveDown(): Unit =
    setState(gameState.copy(activePiece =
      gameState.activePiece.copy(location =
        gameState.activePiece.location + CellIndex(0, -1, 1))))

  private def canMoveDown: Boolean =
    gameState.activePiece.cellIndexes
      .forall(cellIndex => gameState.grid.contains(cellIndex + CellIndex(0, -1, 1)))

  def movePiece(): Unit = {
    removePieceFromGrid()

    if (canMoveDown) {
      moveDown()
    }

    placePieceOnGrid()
  }

  def getState: GameState =
    gameState
  def updateTimestamp(timestamp: Long): Unit =
    gameState = gameState.copy(lastTimestamp = timestamp)
}
