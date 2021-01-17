package triangle_tetris.game

import triangle_tetris.game.pieces.Piece
import triangle_tetris.geometry.Point

import scala.math._

class Game(gridWidth: Double,
           gridHeight: Double,
           magnitude: Double,
           frameRate: Double) {

  private val grid = Grid(gridWidth, gridHeight, magnitude)
  private val _eventHandler = new EventHandler(
    moveRight,
    moveLeft,
    moveDown,
    rotateRight,
    rotateLeft)
  private def newPiece = Piece()

  private var state = GameState(grid, newPiece, newPiece)
  println(state)

  private def setState(newState: GameState) = {
    println(newState)
    state = newState
  }

  def width: Double = grid.width
  def height: Double = grid.height
  def eventHandler: EventHandler = _eventHandler

  private def moveRight(): Unit =
    setState(state.copy(currentPiece = state.currentPiece
      .transpose(Point(grid.cellHeight, 0))))

  private def moveLeft(): Unit =
    setState(state.copy(currentPiece = state.currentPiece
      .transpose(Point(-grid.cellHeight, 0))))

  private def moveDown(): Unit =
    setState(state.copy(currentPiece = state.currentPiece
      .transpose(Point(0, -magnitude))))

  private def rotateRight(): Unit =
    setState(state.copy(currentPiece = state.currentPiece
      .rotate(toRadians(-60))))

  private def rotateLeft(): Unit =
    setState(state.copy(currentPiece = state.currentPiece
      .rotate(toRadians(60))))

  private def updateTimestamp(timestamp: Long) =
    setState(state.copy(lastTimestamp = timestamp))

  def cycle(timestamp: Long): GameState = {
    if (timestamp - state.lastTimestamp >= frameRate) {
      moveDown()
      updateTimestamp(timestamp)
    }

    state
  }

  /*
    - have methods for making changes to game state
      - corresponds to key press events
    - another method for executing a cycle
    - and another for retrieving game state
    - need to keep track of current piece (and eventually next piece)
    - with no events, a cycle will move the current piece down at configured rate
      - need check for reaching the bottom of the grid
    - once current piece has reached the bottom, replace it with a new piece

    - going to need to break pieces up as lines clear
    - maybe once a piece has stopped moving it get decomposed into a different
      kind of game element?
    - maybe something like DecomposedPieces
      - current modeling for color will be an issue
      - currently a single color applies to all primitives in a GameElement
    - or maybe a piece gets broken down into single primitive game elements
      - can keep color modeling
      - might be easier to do intersections
      - game can have a list of DecomposedPieces

    - should the game render itself?
    - or return graphical elements to renderer?
    - or be given a renderer?
    - maybe pass the Scene back?
    - JFXApp probably doesn't have to be on the main object?
      - or maybe it does...

    - yes Game should only have game-related params, not pixels
    - maintains Scene with Node hierarchy
    - Scene can be passed for rendering
    - or maybe Screen is also passed as a param?

    - where should game loop be?
    - maybe have Game and GameRunner?

    val game = nem Game(gridWidth, gridHeight, cellMagnitude)
    val scene = game.scene

   */
}
