package triangle_tetris.game

import triangle_tetris.game.board.Grid

class Game(width: Int,
           height: Int,
           frameRate: Double) {

  private val gameState = new GameStateManager(width, height, frameRate)

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
    gameState.movePiece()

  private def rotateRight(): Unit = ???

  private def rotateLeft(): Unit = ???

  def cycle(timestamp: Long): Grid = {
    if (timestamp - gameState.getState.lastTimestamp >= frameRate) {
      moveDown()
      gameState.updateTimestamp(timestamp)
    }

    gameState.getState.grid
  }
}
