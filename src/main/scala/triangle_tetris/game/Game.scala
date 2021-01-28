package triangle_tetris.game

import triangle_tetris.game.board.{CellIndex, Grid}

class Game(width: Int,
           height: Int,
           frameRate: Double) {

  private val gameStateManager = new GameStateManager(width, height, frameRate)

  private val _eventHandler = new EventHandler(
    moveRight,
    moveLeft,
    moveDown,
    rotateRight,
    rotateLeft)

  def eventHandler: EventHandler = _eventHandler

  private def moveRight(): Unit = ???

  private def moveLeft(): Unit = ???

  private def moveDown(): Unit =
    gameStateManager.movePiece()

  private def rotateRight(): Unit = ???

  private def rotateLeft(): Unit = ???

  def cycle(timestamp: Long): Grid = {
    if (timestamp - gameStateManager.lastTimestamp >= frameRate) {
      moveDown()
      gameStateManager.updateTimestamp(timestamp)
    }

    gameStateManager.grid
  }
}
