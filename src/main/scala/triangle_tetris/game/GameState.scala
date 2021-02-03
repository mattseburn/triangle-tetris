package triangle_tetris.game

import triangle_tetris.game.board.grid.{Cell, Grid}
import triangle_tetris.game.board.movement.{Direction, RotationalDirection}
import triangle_tetris.game.board.ActivePiece

case class GameState(grid: Grid,
                     activePiece: ActivePiece,
                     paused: Boolean = false,
                     lastTimestamp: Long = 0L) {

  def canMove(direction: Direction): Boolean = {
    try { grid.empty(activePiece.move(direction).cellIndexes.diff(activePiece.cellIndexes)) }
    catch { case e: IndexOutOfBoundsException => false }
  }

  def togglePause: GameState =
    this.copy(paused = !paused)

  def move(direction: Direction): GameState =
    if (canMove(direction)) this
      .removeActivePieceFromBoard
      ._move(direction)
      .placeActivePieceOnBoard
    else if (canMove(Direction.Down)) this
    else this
      .copy(activePiece = ActivePiece(grid))

  def rotate(rotationalDirection: RotationalDirection): GameState =
    this
      .removeActivePieceFromBoard
      ._rotate(rotationalDirection)
      .placeActivePieceOnBoard

  def placeActivePieceOnBoard: GameState =
    this.copy(grid = grid.update(activePiece.cells))

  def removeActivePieceFromBoard: GameState =
    this.copy(grid = grid.update(activePiece.cellIndexes.map((_, Cell(None))).toMap))

  def setTimestamp(timestamp: Long): GameState =
    this.copy(lastTimestamp = timestamp)

  private def _move(direction: Direction): GameState =
    this.copy(activePiece = activePiece.move(direction))

  private def _rotate(rotationalDirection: RotationalDirection): GameState =
    this.copy(activePiece = activePiece.rotate(rotationalDirection))
}

object GameState {
  def apply(grid: Grid): GameState =
    GameState(grid, ActivePiece(grid))
      .placeActivePieceOnBoard
}
