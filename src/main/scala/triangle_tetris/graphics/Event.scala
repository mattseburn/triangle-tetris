package triangle_tetris.graphics

import javafx.scene.input.KeyCode

sealed trait Event
case object Event {
  case object MoveLeft extends Event
  case object MoveRight extends Event
  case object MoveDown extends Event
  case object RotateRight extends Event
  case object RotateLeft extends Event
  case object Unimplemented extends Event

  def apply(keyCode: KeyCode): Event = keyCode match {
    case KeyCode.LEFT => MoveLeft
    case KeyCode.RIGHT => MoveRight
    case KeyCode.DOWN => MoveDown
    case KeyCode.PAGE_DOWN => RotateRight
    case KeyCode.PAGE_UP => RotateLeft
    case _ => Unimplemented
  }
}
