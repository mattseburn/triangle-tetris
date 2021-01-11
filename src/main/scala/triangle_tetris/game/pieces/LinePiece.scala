package triangle_tetris.game.pieces

import triangle_tetris.geometry.{Point, Triangle}

case class LinePiece(location: Point = Piece.defaultLocation,
                     triangles: List[Triangle] = List(),
                     magnitude: Double = Piece.defaultMagnitude)
  extends Piece(PieceType.Line, location, triangles, magnitude) {

  def rotate(angle: Double): LinePiece =
    LinePiece(location, triangles.map(_.rotate(angle)))

  def transpose(delta: Point): LinePiece =
    LinePiece(location.transpose(delta), triangles.map(_.transpose(delta)))

  def resize(size: Double): LinePiece =
    this
}

object LinePiece {
  def apply(): LinePiece =
    LinePiece(Piece.defaultLocation, List(
      Piece.equilateralTriangle,
      Piece.equilateralTriangle.transpose(Point(0, Piece.defaultMagnitude)),
      Piece.equilateralTriangle.transpose(Point(0, -Piece.defaultMagnitude)),
      Piece.equilateralTriangle
        .rotate(Piece.defaultLocation, -Piece.angle)
        .transpose(Point(0, Piece.defaultMagnitude)),
      Piece.equilateralTriangle.rotate(Piece.defaultLocation, -Piece.angle),
      Piece.equilateralTriangle
        .rotate(Piece.defaultLocation, -Piece.angle)
        .transpose(Point(0, -Piece.defaultMagnitude))))
}
