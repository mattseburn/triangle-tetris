package triangle_tetris.game

import triangle_tetris.game.pieces.Piece

case class GameState(grid: Grid,
                     currentPiece: Piece,
                     nextPiece: Piece,
                     lastTimestamp: Long = 0)
