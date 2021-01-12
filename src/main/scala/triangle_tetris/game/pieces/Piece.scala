package triangle_tetris.game.pieces

import triangle_tetris.game.GameElement
import triangle_tetris.geometry.{Point, Transformations, Triangle}

abstract class Piece(pieceType: PieceType,
                     location: Point = GameElement.defaultLocation,
                     triangles: List[Triangle] = List(),
                     magnitude: Double = GameElement.defaultMagnitude
                ) extends GameElement[Triangle]
                  with Transformations[Piece] {

  def children: List[Triangle] =
    triangles
}
