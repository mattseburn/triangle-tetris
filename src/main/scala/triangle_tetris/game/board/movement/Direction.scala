package triangle_tetris.game.board.movement

sealed trait Direction
case object Direction {
  case object Left extends Direction
  case object Right extends Direction
  case object Down extends Direction
}
