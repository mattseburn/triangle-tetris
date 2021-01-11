package triangle_tetris.game.pieces

import triangle_tetris.geometry.{Point, Transformations, Triangle}
import triangle_tetris.scene.Node

import scala.math._

abstract class Piece(pieceType: PieceType,
                     location: Point = Piece.defaultLocation,
                     triangles: List[Triangle] = List(),
                     magnitude: Double = Piece.defaultMagnitude
                ) extends Transformations[Piece] {

  val node: Node =
    Node(None, triangles.map(t => Node(Some(t), List())))
}

object Piece {
  val defaultLocation: Point = Point(0, 0)
  val defaultMagnitude: Double = 10
  val angle: Double = toRadians(60)

  def triangleHeight(magnitude: Double = defaultMagnitude): Double =
    rint(sqrt(pow(magnitude, 2) - pow(magnitude/2, 2)))

  def equilateralTriangle: Triangle =
    Triangle(
      defaultLocation,
      Point(defaultLocation.x, defaultLocation.y - defaultMagnitude),
      Point(defaultLocation.x - triangleHeight(defaultMagnitude), defaultLocation.y - defaultMagnitude/2))
}
