package triangle_tetris.game.pieces

import triangle_tetris.game.pieces.Piece._

sealed trait Color
case object Color {
  case object Red extends Color
  case object Blue extends Color
  case object Green extends Color
  case object FireBrick extends Color
  case object Cyan extends Color
  case object Magenta extends Color
  case object SkyBlue extends Color
  case object DeepPink extends Color
  case object SpringGreen extends Color
  case object BlueViolet extends Color
  case object OrangeRed extends Color
  case object Gold extends Color

  case object White extends Color
  case object Black extends Color

  def apply(piece: Piece): Color = piece match {
    case BoatPiece => SkyBlue
    case ChevronPiece => Cyan
    case HexagonPiece => Red
    case FoxPiece => BlueViolet
    case HourGlassPiece => Blue
    case LinePiece => Green
    case MountainPiece => OrangeRed
    case PistolPiece => DeepPink
    case RocketPiece => FireBrick
    case SnailPiece => SpringGreen
    case SnakePiece => Magenta
    case SwitchbladePiece => Gold
  }
}
