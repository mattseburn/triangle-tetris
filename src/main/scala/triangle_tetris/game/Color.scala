package triangle_tetris.game

sealed trait Color
case object Color {
  case object Red extends Color
  case object Blue extends Color
  case object Green extends Color
  case object White extends Color
  case object Black extends Color
}
