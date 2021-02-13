package triangle_tetris.screen

import scalafx.scene.paint.{Color => FxColor}
import triangle_tetris.game.pieces.Color

object ScreenColor {
  private val backgroundColor = FxColor.Black

  def apply(color: Option[Color]): FxColor = color.map {
    case Color.Red => FxColor.Red
    case Color.Blue => FxColor.Blue
    case Color.Green => FxColor.Green
    case Color.FireBrick => FxColor.FireBrick
    case Color.Cyan => FxColor.Cyan
    case Color.Magenta => FxColor.Magenta
    case Color.SkyBlue => FxColor.SkyBlue
    case Color.DeepPink => FxColor.DeepPink
    case Color.SpringGreen => FxColor.SpringGreen
    case Color.BlueViolet => FxColor.BlueViolet
    case Color.OrangeRed => FxColor.OrangeRed
    case Color.Gold => FxColor.Gold

    case Color.White => FxColor.White
    case Color.Black => FxColor.Black
  }.getOrElse(backgroundColor)
}
