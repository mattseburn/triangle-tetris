package triangle_tetris.graphics

import triangle_tetris.game.pieces.Color
import scalafx.scene.paint.{Color => FxColor}

object ScreenColor {
  def apply(color: Color): FxColor = color match {
    case Color.Red => FxColor.Red
    case Color.Blue => FxColor.Blue
    case Color.Green => FxColor.Green
    case Color.White => FxColor.White
  }
}
