package triangle_tetris.game

import triangle_tetris.game.board.{ActivePiece, Cell, Direction, Grid}

case class GameState(grid: Grid,
                     activePiece: ActivePiece,
                     lastTimestamp: Long = 0L) {

  def canMove(direction: Direction): Boolean = {
    try { grid.empty(activePiece.move(direction).cellIndexes.diff(activePiece.cellIndexes)) }
    catch { case e: IndexOutOfBoundsException => false }
  }

  def move(direction: Direction): GameState =
    if (canMove(direction)) this
      .removeActivePieceFromBoard
      ._move(direction)
      .placeActivePieceOnBoard
    else if (canMove(Direction.Down)) this
    else this
      .copy(activePiece = ActivePiece(grid))

  def placeActivePieceOnBoard: GameState =
    this.copy(grid = grid.update(activePiece.cells))

  def removeActivePieceFromBoard: GameState =
    this.copy(grid = grid.update(activePiece.cellIndexes.map((_, Cell(None))).toMap))

  def setTimestamp(timestamp: Long): GameState =
    this.copy(lastTimestamp = timestamp)

  private def _move(direction: Direction): GameState =
    GameState(grid, activePiece.move(direction), lastTimestamp)
}

object GameState {
  def apply(grid: Grid): GameState =
    GameState(grid, ActivePiece(grid))
      .placeActivePieceOnBoard
}
