package triangle_tetris.game

import triangle_tetris.game.pieces.Color
import triangle_tetris.geometry.{Point, Primitive, Triangle}

import scala.math.{pow, rint, sqrt, toRadians}

abstract class GameElement[P <: Primitive](val primitives: List[P] = List(),
                                           val fillColor: Option[Color] = None,
                                           val strokeColor: Option[Color] = None)

object GameElement {
  val defaultLocation: Point = Point(0, 0)
  val defaultMagnitude: Double = 50
  val angle: Double = toRadians(60)

  def triangleHeight(magnitude: Double = defaultMagnitude): Double =
    rint(sqrt(pow(magnitude, 2) - pow(magnitude/2, 2)))

  def equilateralTriangle: Triangle =
    Triangle(
      defaultLocation,
      Point(defaultLocation.x, defaultLocation.y - defaultMagnitude),
      Point(defaultLocation.x - triangleHeight(defaultMagnitude), defaultLocation.y - defaultMagnitude/2))
}
