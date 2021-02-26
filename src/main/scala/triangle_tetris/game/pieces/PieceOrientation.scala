package triangle_tetris.game.pieces

import scala.util.Random

sealed trait PieceOrientation
case object PieceOrientation {
  case object Left extends PieceOrientation
  case object Right extends PieceOrientation

  private val allPieceOrientations =
    List(Left, Right)

  def apply(): PieceOrientation =
    allPieceOrientations(Random.nextInt(allPieceOrientations.size))
}
