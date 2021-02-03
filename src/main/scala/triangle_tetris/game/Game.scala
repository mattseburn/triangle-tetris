package triangle_tetris.game

import com.typesafe.scalalogging.Logger
import triangle_tetris.events.Event._
import triangle_tetris.events.{Event, EventSystem}
import triangle_tetris.game.board.movement.Direction._
import triangle_tetris.game.board.movement.{Direction, RotationalDirection}
import triangle_tetris.game.board.grid.Grid
import triangle_tetris.game.board.movement.RotationalDirection._

class Game(width: Int,
           height: Int,
           frameRate: Double,
           eventSystem: EventSystem) {

  private var _gameState = GameState(Grid(width, height))
  private val logger = Logger[Game]

  eventSystem.registerHandler(MoveRight, () => move(Right))
  eventSystem.registerHandler(MoveLeft, () => move(Left))
  eventSystem.registerHandler(MoveDown, () => move(Down))
  eventSystem.registerHandler(RotateRight, () => rotate(Clockwise))
  eventSystem.registerHandler(RotateLeft, () => rotate(Counterclockwise))
  eventSystem.registerHandler(TogglePause, togglePause)

  private def move(direction: Direction): Unit =
    if (!_gameState.paused)
      _gameState = _gameState.move(direction)

  private def rotate(rotationalDirection: RotationalDirection): Unit =
    if (!_gameState.paused)
      _gameState = _gameState.rotate(rotationalDirection)

  private def togglePause(): Unit =
    _gameState = _gameState.togglePause

  private def updateTimestamp(timestamp: Long): Unit =
    _gameState = _gameState.copy(lastTimestamp = timestamp)

  def cycle(timestamp: Long): Grid = {
    if (!_gameState.paused && timestamp - _gameState.lastTimestamp >= frameRate) {
      logger.debug(s"Game cycle [$timestamp]")
      move(Down)
      updateTimestamp(timestamp)
    }

    _gameState.grid
  }
}
