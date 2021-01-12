package triangle_tetris.game.pieces

import triangle_tetris.game.GameElement
import triangle_tetris.geometry.{Point, Triangle}

case class HexagonPiece(location: Point = GameElement.defaultLocation,
                        triangles: List[Triangle] = List(),
                        magnitude: Double = GameElement.defaultMagnitude)
  extends Piece(PieceType.Hexagon, location, triangles) {

  def rotate(angle: Double): HexagonPiece =
    HexagonPiece(location, triangles.map(_.rotate(angle)))

  def transpose(delta: Point): HexagonPiece =
    HexagonPiece(location.transpose(delta), triangles.map(_.transpose(delta)))

  def resize(size: Double): HexagonPiece =
    this
}

object HexagonPiece {
  import GameElement._

  def apply(): HexagonPiece =
    HexagonPiece(defaultLocation, List(
      equilateralTriangle,
      equilateralTriangle.rotate(defaultLocation, angle),
      equilateralTriangle.rotate(defaultLocation, 2*angle),
      equilateralTriangle.rotate(defaultLocation, 3*angle),
      equilateralTriangle.rotate(defaultLocation, 4*angle),
      equilateralTriangle.rotate(defaultLocation, 5*angle)))
}
