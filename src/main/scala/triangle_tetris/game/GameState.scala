package triangle_tetris.game

import triangle_tetris.game.board.{ActivePiece, Cell, Grid}
import triangle_tetris.game.pieces.Piece

case class GameState(grid: Grid,
                     activePiece: ActivePiece = ActivePiece(Piece()),
                     lastTimestamp: Long = 0) {

  private def removePiece(): GameState =
    GameState(
      grid.update(activePiece.cellIndexes.map((_, Cell(None))).toMap),
      activePiece,
      lastTimestamp)

  private def addPiece(): GameState =
    GameState(grid.update(activePiece.cells), activePiece, lastTimestamp)

  private def newPiece(): GameState =
    GameState(grid, ActivePiece(Piece()), lastTimestamp)

  private def updatePiece(): GameState =
    this.removePiece().newPiece().addPiece()
}

object GameState {
  def apply(grid: Grid): GameState =
    new GameState(grid).updatePiece()
}
