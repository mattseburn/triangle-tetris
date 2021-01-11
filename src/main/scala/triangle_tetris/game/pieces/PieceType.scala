package triangle_tetris.game.pieces

sealed trait PieceType
case object PieceType {
  def apply(s: String): PieceType = s match {
    case "Hexagon" => PieceType.Hexagon
    case "Line" => PieceType.Line
    case _ => throw new IllegalArgumentException(s"Unrecognized piece type: '$s'")
  }

  case object Hexagon extends PieceType {
    def apply(): HexagonPiece =
      HexagonPiece()
  }
  case object Line extends PieceType {
    def apply(): LinePiece =
      LinePiece()
  }
  case object HourGlass extends PieceType {
    def apply(): HourGlassPiece =
      HourGlassPiece()
  }
}

