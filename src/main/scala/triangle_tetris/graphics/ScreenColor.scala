package triangle_tetris.graphics

import scalafx.scene.paint.{Color => FxColor}
import triangle_tetris.game.Color

object ScreenColor {
  private val backgroundColor = FxColor.Black

  def apply(color: Option[Color]): FxColor = color.map {
    case Color.Red => FxColor.Red
    case Color.Blue => FxColor.Blue
    case Color.Green => FxColor.Green
    case Color.White => FxColor.White
    case Color.Black => FxColor.Black
  }.getOrElse(backgroundColor)
}
