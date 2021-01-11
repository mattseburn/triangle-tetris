package triangle_tetris.game.pieces

import triangle_tetris.geometry.{Point, Triangle}

case class HourGlassPiece(location: Point = Piece.defaultLocation,
                          triangles: List[Triangle] = List(),
                          magnitude: Double = Piece.defaultMagnitude)
  extends Piece(PieceType.HourGlass, location, triangles) {

  def rotate(angle: Double): HourGlassPiece =
    HourGlassPiece(location, triangles.map(_.rotate(angle)))

  def transpose(delta: Point): HourGlassPiece =
    HourGlassPiece(location.transpose(delta), triangles.map(_.transpose(delta)))

  def resize(size: Double): HourGlassPiece =
    this
}

object HourGlassPiece {
  def apply(): HourGlassPiece =
    HourGlassPiece(Piece.defaultLocation, List(
      Piece.equilateralTriangle,
      Piece.equilateralTriangle.rotate(Piece.defaultLocation, Piece.angle),
      Piece.equilateralTriangle.rotate(Piece.defaultLocation, 2*Piece.angle),
      Piece.equilateralTriangle.rotate(Piece.defaultLocation, 3*Piece.angle),
      Piece.equilateralTriangle
        .rotate(Piece.defaultLocation, 2*Piece.angle)
        .transpose(Point(0, -Piece.defaultMagnitude)),
      Piece.equilateralTriangle
        .rotate(Piece.defaultLocation, Piece.angle)
        .transpose(Point(Piece.triangleHeight(), Piece.defaultMagnitude/2))
    ))
}
