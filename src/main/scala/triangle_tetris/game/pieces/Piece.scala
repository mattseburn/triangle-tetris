package triangle_tetris.game.pieces

import scala.util.Random

sealed trait Piece
case object Piece {
  case object BoatPiece extends Piece
  case object ChevronPiece extends Piece
  case object FoxPiece extends Piece
  case object HexagonPiece extends Piece
  case object HourGlassPiece extends Piece
  case object LinePiece extends Piece
  case object MountainPiece extends Piece
  case object PistolPiece extends Piece
  case object RocketPiece extends Piece
  case object SnailPiece extends Piece
  case object SnakePiece extends Piece
  case object SwitchbladePiece extends Piece

  private val allPieces: List[Piece] = List(
    BoatPiece,
    ChevronPiece,
    FoxPiece,
    HexagonPiece,
    HourGlassPiece,
    LinePiece,
    MountainPiece,
    PistolPiece,
    RocketPiece,
    SnailPiece,
    SnakePiece,
    SwitchbladePiece)

  def apply(): Piece =
    allPieces(Random.nextInt(allPieces.size))
}
