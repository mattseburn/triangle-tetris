package triangle_tetris.scene

import triangle_tetris.game.GameElement
import triangle_tetris.game.pieces.Color
import triangle_tetris.geometry.Primitive

case class SceneElement(primitives: List[Primitive],
                        fillColor: Option[Color],
                        strokeColor: Option[Color])

object SceneElement {
  def apply[P <: Primitive](gameElement: GameElement[P]): SceneElement =
    SceneElement(gameElement.primitives, gameElement.fillColor, gameElement.strokeColor)
}