package triangle_tetris.game.pieces

import triangle_tetris.game.GameElement
import triangle_tetris.geometry.{Point, Triangle}

case class HourGlassPiece(location: Point = GameElement.defaultLocation,
                          triangles: List[Triangle] = List(),
                          magnitude: Double = GameElement.defaultMagnitude)
  extends Piece(PieceType.HourGlass, Color.Blue, location, triangles) {

  def rotate(angle: Double): HourGlassPiece =
    HourGlassPiece(location, triangles.map(_.rotate(angle)))

  def transpose(delta: Point): HourGlassPiece =
    HourGlassPiece(location.transpose(delta), triangles.map(_.transpose(delta)))

  def resize(size: Double): HourGlassPiece =
    this
}

object HourGlassPiece {
  import GameElement._

  def apply(): HourGlassPiece =
    HourGlassPiece(defaultLocation, List(
      equilateralTriangle,
      equilateralTriangle.rotate(defaultLocation, angle),
      equilateralTriangle.rotate(defaultLocation, 2*angle),
      equilateralTriangle.rotate(defaultLocation, 3*angle),
      equilateralTriangle
        .rotate(defaultLocation, 2*angle)
        .transpose(Point(0, -defaultMagnitude)),
      equilateralTriangle
        .rotate(defaultLocation, angle)
        .transpose(Point(triangleHeight(), defaultMagnitude/2))
    ))
}
