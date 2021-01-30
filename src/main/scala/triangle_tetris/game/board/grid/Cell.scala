package triangle_tetris.game.board.grid

import triangle_tetris.game.Color

case class Cell(color: Option[Color] = None) {
  override def toString: String =
    s"Cell(${color.getOrElse("")})"
}
