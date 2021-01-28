package triangle_tetris.game

import triangle_tetris.game.board.{ActivePiece, Cell, Grid}

class GameStateManager(width: Int,
                       height: Int,
                       frameRate: Double) {

  private var _lastTimeStamp = 0L
  private var _grid = Grid(width, height)
  private var _activePiece = newPiece

  private def newPiece =
    ActivePiece(_grid)

  private def removePieceFromGrid(): Unit =
      _grid = _grid
        .update(_activePiece.cellIndexes.map((_, Cell(None))).toMap)

  private def placePieceOnGrid(): Unit =
    _grid = _grid.update(_activePiece.cells)

  private def moveDown(): Unit =
    _activePiece = _activePiece.moveDown

  private def canMoveDown: Boolean =
    _grid.contains(_activePiece.moveDown.cellIndexes)

  def movePiece(): Unit = {
    removePieceFromGrid()

    if (canMoveDown) {
      moveDown()
    }

    placePieceOnGrid()
  }

  def grid: Grid = _grid
  def lastTimestamp = _lastTimeStamp
  def updateTimestamp(timestamp: Long): Unit =
    _lastTimeStamp = timestamp
}
