package triangle_tetris.game.board.grid

sealed trait Axis
case object Axis {
  case object I extends Axis
  case object J extends Axis
  case object K extends Axis
}
