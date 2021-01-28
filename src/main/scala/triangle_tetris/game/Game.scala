package triangle_tetris.game

import triangle_tetris.game.board.Grid
import triangle_tetris.game.board.Direction._

class Game(width: Int,
           height: Int,
           frameRate: Double) {

  private var _gameState = GameState(Grid(width, height))

  private val _eventHandler = new EventHandler(
    moveRight,
    moveLeft,
    moveDown,
    rotateRight,
    rotateLeft)

  def eventHandler: EventHandler = _eventHandler

  private def moveRight(): Unit =
    _gameState = _gameState.move(Right)

  private def moveLeft(): Unit =
    _gameState = _gameState.move(Left)

  private def moveDown(): Unit =
    _gameState = _gameState.move(Down)

  private def rotateRight(): Unit = ???

  private def rotateLeft(): Unit = ???

  private def updateTimestamp(timestamp: Long): Unit =
    _gameState = _gameState.copy(lastTimestamp = timestamp)

  def cycle(timestamp: Long): Grid = {
    if (timestamp - _gameState.lastTimestamp >= frameRate) {
      moveDown()
      updateTimestamp(timestamp)
    }

    _gameState.grid
  }
}
