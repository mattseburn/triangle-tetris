package triangle_tetris.game.board.movement

sealed trait RotationalDirection
case object RotationalDirection {
  case object Clockwise extends RotationalDirection
  case object Counterclockwise extends RotationalDirection
}
