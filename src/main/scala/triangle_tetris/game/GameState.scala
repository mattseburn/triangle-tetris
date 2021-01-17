package triangle_tetris.game

import triangle_tetris.game.pieces.Piece

case class GameState(grid: Grid,
                     currentPiece: Piece,
                     nextPiece: Piece,
                     lastTimestamp: Long = 0) {

  override def toString: String =
    s"GameState[$currentPiece]"
}
