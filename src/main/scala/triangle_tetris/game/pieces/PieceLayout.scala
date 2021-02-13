package triangle_tetris.game.pieces

import triangle_tetris.game.board.grid.CellIndex
import triangle_tetris.game.pieces.Piece._

case class PieceLayout(cells: List[CellIndex])

object PieceLayout {
  def apply(piece: Piece): PieceLayout =
    new PieceLayout(piece match {
      case BoatPiece => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -2, 0),
        CellIndex(0, -1, -1),
        CellIndex(1, 0, 0))

      case ChevronPiece => List(
        CellIndex(0, 0, 0),
        CellIndex(1, 0, 0),
        CellIndex(-2, -1, 1),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case FoxPiece => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -1, 1),
        CellIndex(-1, -2, 0),
        CellIndex(0, 0, 1))

      case HexagonPiece => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case HourGlassPiece => List(
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1),
        CellIndex(0, 1, 1),
        CellIndex(0, -1, 0),
        CellIndex(0, 0, 0),
        CellIndex(1, 0, 0))

      case LinePiece => List(
        CellIndex(0, 1, 1),
        CellIndex(0, 0, 1),
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(0, -1, -1),
        CellIndex(0, -2, -1))

      case MountainPiece => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -2, 0),
        CellIndex(1, 0, 0),
        CellIndex(0, 0, 1))

      case PistolPiece => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -2, 0),
        CellIndex(0, -1, -1),
        CellIndex(0, 0, 1))

      case RocketPiece => List(
        CellIndex(0, 0, 0),
        CellIndex(-1, 0, 2),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case SnailPiece => List(
        CellIndex(0, 0, 0),
        CellIndex(1, 0, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case SnakePiece => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -1, 1),
        CellIndex(-2, -2, 1),
        CellIndex(-2, -1, 1))

      case SwitchbladePiece => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(1, -1, -1),
        CellIndex(0, -1, -1),
        CellIndex(0, 1, 1),
        CellIndex(0, 0, 1))
    })
}
