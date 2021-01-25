package triangle_tetris.game

sealed trait Color
case object Color {
  def apply(s: String): Color = s match {
    case "Red" => Color.Red
    case "Blue" => Color.Blue
    case "Green" => Color.Green
    case "White" => Color.White
    case "Black" => Color.Black
    case _ => throw new IllegalArgumentException(s"Unrecognized color: '$s'")
  }

  case object Red extends Color
  case object Blue extends Color
  case object Green extends Color
  case object White extends Color
  case object Black extends Color
}
