package triangle_tetris.game

import triangle_tetris.game.board.{ActivePiece, Cell, CellIndex, Grid}
import triangle_tetris.game.pieces.Piece

import scala.util.Random

class GameStateManager(width: Int,
                       height: Int,
                       frameRate: Double) {

  private var lastTimestamp = 0L
  private var grid = Grid(width, height)
  private var activePiece = newPiece

  private def newPiece =
    ActivePiece(Piece(), grid.columnHeads.head)
//  private var gameState =
//    GameState(Grid(width, height))

//  private def startingLocation =
//    gameState.grid.columnHeads(Random.nextInt(gameState.grid.columnHeads.length))

  private def setState(newGrid: Grid): Unit =
    grid = newGrid

  private def removePieceFromGrid(): Unit =
      grid = grid
        .update(activePiece.cellIndexes.map((_, Cell(None))).toMap)

  private def placePieceOnGrid(): Unit =
    grid = grid.update(activePiece.cells)

  private def setNewPiece(): Unit =
    activePiece = newPiece

  private def moveDown(): Unit =
    activePiece = activePiece.copy(location =
        activePiece.location + CellIndex(0, -1, 1))

  private def canMoveDown: Boolean =
    activePiece.cellIndexes
      .forall(cellIndex => grid.contains(cellIndex + CellIndex(0, -1, 1)))

  def movePiece(): Unit = {
    removePieceFromGrid()

    if (canMoveDown) {
      moveDown()
    }

    placePieceOnGrid()
  }

  def getGrid: Grid = grid
  def getLastTimestamp = lastTimestamp
  def updateTimestamp(timestamp: Long): Unit =
    lastTimestamp = timestamp
}
