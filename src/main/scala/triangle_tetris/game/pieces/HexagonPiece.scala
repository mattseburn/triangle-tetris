package triangle_tetris.game.pieces

import triangle_tetris.geometry.{Point, Triangle}

case class HexagonPiece(location: Point = Piece.defaultLocation,
                        triangles: List[Triangle] = List(),
                        magnitude: Double = Piece.defaultMagnitude)
  extends Piece(PieceType.Hexagon, location, triangles) {

  def rotate(angle: Double): Piece =
    HexagonPiece(location, triangles.map(_.rotate(angle)))

  def transpose(delta: Point): Piece =
    HexagonPiece(location.transpose(delta), triangles.map(_.transpose(delta)))
}

object HexagonPiece {
  def apply(): HexagonPiece =
    HexagonPiece(Piece.defaultLocation, List(
      Piece.equilateralTriangle,
      Piece.equilateralTriangle.rotate(Piece.defaultLocation, Piece.angle),
      Piece.equilateralTriangle.rotate(Piece.defaultLocation, 2*Piece.angle),
      Piece.equilateralTriangle.rotate(Piece.defaultLocation, 3*Piece.angle),
      Piece.equilateralTriangle.rotate(Piece.defaultLocation, 4*Piece.angle),
      Piece.equilateralTriangle.rotate(Piece.defaultLocation, 5*Piece.angle)))
}
