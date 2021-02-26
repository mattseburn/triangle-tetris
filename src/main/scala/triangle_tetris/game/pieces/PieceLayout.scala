package triangle_tetris.game.pieces

import triangle_tetris.game.board.grid.CellIndex
import triangle_tetris.game.pieces.Piece._
import triangle_tetris.game.pieces.PieceOrientation._

case class PieceLayout(cells: List[CellIndex])

object PieceLayout {
  def apply(piece: Piece, pieceOrientation: PieceOrientation): PieceLayout =
    new PieceLayout((piece, pieceOrientation) match {
      case (BoatPiece, _) => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -2, 0),
        CellIndex(0, -1, -1),
        CellIndex(1, 0, 0))

      case (ChevronPiece, _) => List(
        CellIndex(0, 0, 0),
        CellIndex(1, 0, 0),
        CellIndex(-2, -1, 1),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case (FoxPiece, _) => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -1, 1),
        CellIndex(-1, -2, 0),
        CellIndex(0, 0, 1))

      case (HexagonPiece, _) => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case (HourGlassPiece, _) => List(
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1),
        CellIndex(0, 1, 1),
        CellIndex(0, -1, 0),
        CellIndex(0, 0, 0),
        CellIndex(1, 0, 0))

      case (LinePiece, Left) => List(
        CellIndex(-1, 0, 2),
        CellIndex(-1, 0, 1),
        CellIndex(-1, -1, 1),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -2, 0),
        CellIndex(-1, -2, -1))

      case (LinePiece, Right) => List(
        CellIndex(0, 1, 1),
        CellIndex(0, 0, 1),
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(0, -1, -1),
        CellIndex(0, -2, -1))

      case (MountainPiece, Left) => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -2, 0),
        CellIndex(1, 0, 0),
        CellIndex(-1, -1, 1))

      case (MountainPiece, Right) => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -2, 0),
        CellIndex(1, 0, 0),
        CellIndex(0, 0, 1))

      case (PistolPiece, Left) => List(
        CellIndex(-1, -1, 1),
        CellIndex(-1, -1, 0),
        CellIndex(0, -1, 0),
        CellIndex(0, -1, -1),
        CellIndex(-1, -2, 0),
        CellIndex(-1, 0, 1))

      case (PistolPiece, Right) => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -2, 0),
        CellIndex(0, -1, -1),
        CellIndex(0, 0, 1))

      case (RocketPiece, _) => List(
        CellIndex(0, 0, 0),
        CellIndex(-1, 0, 2),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case (SnailPiece, Left) => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(-2, -1, 1),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case (SnailPiece, Right) => List(
        CellIndex(0, 0, 0),
        CellIndex(1, 0, 0),
        CellIndex(-1, -1, 0),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case (SnakePiece, Left) => List(
        CellIndex(0, 0, 0),
        CellIndex(-2, -1, 2),
        CellIndex(-2, -1, 1),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case (SnakePiece, Right) => List(
        CellIndex(0, 0, 0),
        CellIndex(1, 0, 0),
        CellIndex(1, 1, 0),
        CellIndex(-1, -1, 1),
        CellIndex(-1, 0, 1),
        CellIndex(0, 0, 1))

      case (SwitchbladePiece, Left) => List(
        CellIndex(0, 0, 0),
        CellIndex(0, -1, 0),
        CellIndex(1, -1, -1),
        CellIndex(0, -1, -1),
        CellIndex(0, 1, 1),
        CellIndex(0, 0, 1))

      case (SwitchbladePiece, Right) => List(
        CellIndex(-1, -1, 1),
        CellIndex(-1, -1, 0),
        CellIndex(-2, -2, 0),
        CellIndex(-1, -2, 0),
        CellIndex(-1, 0, 2),
        CellIndex(-1, 0, 1))
    })
}
