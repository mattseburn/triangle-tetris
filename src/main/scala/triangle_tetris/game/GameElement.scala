package triangle_tetris.game

import triangle_tetris.geometry.{Point, Primitive, Triangle}
import triangle_tetris.scene.Node

import scala.math.{pow, rint, sqrt, toRadians}

trait GameElement[P <: Primitive] {
  def children: List[P]
  val node: Node =
    Node(None, children.map(c => Node(Some(c), List())))
}

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
