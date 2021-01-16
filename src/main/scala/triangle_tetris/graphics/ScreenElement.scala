package triangle_tetris.graphics

import triangle_tetris.game.pieces.Color
import triangle_tetris.geometry.Primitive
import triangle_tetris.scene.SceneElement

case class ScreenElement(primitive: Primitive,
                         fillColor: Option[Color],
                         strokeColor: Option[Color])

object ScreenElement {
  def apply(sceneElement: SceneElement): List[ScreenElement] =
    sceneElement.primitives.map(ScreenElement(_, sceneElement.fillColor, sceneElement.strokeColor))
}
