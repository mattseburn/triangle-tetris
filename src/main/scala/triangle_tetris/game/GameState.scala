package triangle_tetris.game

import triangle_tetris.game.board.grid.{CellIndex, Grid, LineCollection}
import triangle_tetris.game.board.movement.{Direction, RotationalDirection}
import triangle_tetris.game.board.ActivePiece

case class GameState(grid: Grid,
                     activePiece: ActivePiece,
                     lastCompletedLines: LineCollection = LineCollection(),
                     paused: Boolean = false,
                     lastTimestamp: Long = 0L) {

  def togglePause: GameState =
    this.copy(paused = !paused)

  def move(direction: Direction): GameState =
    if (canMove(direction)) this
      .removeActivePieceFromBoard
      ._move(direction)
      .placeActivePieceOnBoard
    else if (canMove(Direction.Down)) this
    else newActivePiece

  def rotate(rotationalDirection: RotationalDirection): GameState =
    if (canRotate(rotationalDirection)) this
      .removeActivePieceFromBoard
      ._rotate(rotationalDirection)
      .placeActivePieceOnBoard
    else this

  def setTimestamp(timestamp: Long): GameState =
    this.copy(lastTimestamp = timestamp)

  def clearCompleteLines: GameState =
    this.copy(
      lastCompletedLines = grid.completeDiagonalLines,
      grid = grid.wipeLines(grid.completeDiagonalLines))

  def collapseEmptyLines: GameState =
    this.copy(
      lastCompletedLines = LineCollection(),
      grid = lastCompletedLines.lines
        .map(grid.partitionByLine(_)._1)
        .fold(grid) {
          case (_grid: Grid, lines: LineCollection) => _grid.dropLines(lines)
        }.asInstanceOf[Grid])

  private def newActivePiece: GameState =
    this.copy(activePiece = ActivePiece(grid))
      .placeActivePieceOnBoard

  private def canMove(direction: Direction): Boolean =
    cellsAvailable(activePiece.move(direction).cellIndexes)

  private def canRotate(rotationalDirection: RotationalDirection): Boolean =
    cellsAvailable(activePiece.rotate(rotationalDirection).cellIndexes)

  private def cellsAvailable(cellIndexes: List[CellIndex]): Boolean =
    try { grid.empty(cellIndexes.diff(activePiece.cellIndexes)) }
    catch { case _: IndexOutOfBoundsException => false }

  private def placeActivePieceOnBoard: GameState =
    this.copy(grid = grid.update(activePiece.cells))

  private def removeActivePieceFromBoard: GameState =
    this.copy(grid = grid.wipeCells(activePiece.cellIndexes))

  private def _move(direction: Direction): GameState =
    this.copy(activePiece = activePiece.move(direction))

  private def _rotate(rotationalDirection: RotationalDirection): GameState =
    this.copy(activePiece = activePiece.rotate(rotationalDirection))
}

object GameState {
  def apply(width: Int, height: Int): GameState =
    GameState(Grid(width, height))

  def apply(grid: Grid): GameState =
    GameState(grid, ActivePiece(grid))
      .placeActivePieceOnBoard
}
