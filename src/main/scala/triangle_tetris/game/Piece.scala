package triangle_tetris.game

import triangle_tetris.geometry.{Point, Triangle}
import triangle_tetris.scene.Node

import scala.math._

case class Piece(location: Point, magnitude: Double) {
  private val angle = toRadians(60)
  private val triangles: List[Triangle] = List(
    createEquilateralTriangle,
    createEquilateralTriangle.rotate(location, angle),
    createEquilateralTriangle.rotate(location, 2*angle),
    createEquilateralTriangle.rotate(location, 3*angle),
    createEquilateralTriangle.rotate(location, 4*angle),
    createEquilateralTriangle.rotate(location, 5*angle)
  )

  val node = Node(None, triangles.map(t => Node(Some(t), List())))

  private def createEquilateralTriangle =
    Triangle(
      location,
      Point(location.x, location.y - c),
      Point(location.x - b, location.y - rint(c/2)))

  private def a =
    rint(magnitude/2)

  private def b =
    rint(sqrt(pow(c, 2) - pow(a, 2)))

  private def c =
    magnitude
}
