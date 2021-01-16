package triangle_tetris.game.pieces

import triangle_tetris.game.GameElement
import triangle_tetris.geometry.{Point, Triangle}

case class LinePiece(location: Point = GameElement.defaultLocation,
                     triangles: List[Triangle] = List(),
                     magnitude: Double = GameElement.defaultMagnitude)
  extends Piece(PieceType.Line, Color.Green, location, triangles, magnitude) {

  def rotate(angle: Double): LinePiece =
    LinePiece(location, triangles.map(_.rotate(angle)))

  def transpose(delta: Point): LinePiece =
    LinePiece(location.transpose(delta), triangles.map(_.transpose(delta)))

  def resize(size: Double): LinePiece =
    this
}

object LinePiece {
  import GameElement._

  def apply(): LinePiece =
    LinePiece(defaultLocation, List(
      equilateralTriangle,
      equilateralTriangle.transpose(Point(0, defaultMagnitude)),
      equilateralTriangle.transpose(Point(0, -defaultMagnitude)),
      equilateralTriangle
        .rotate(defaultLocation, -angle)
        .transpose(Point(0, defaultMagnitude)),
      equilateralTriangle.rotate(defaultLocation, -angle),
      equilateralTriangle
        .rotate(defaultLocation, -angle)
        .transpose(Point(0, -defaultMagnitude))))
}
