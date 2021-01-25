package triangle_tetris.graphics

import scalafx.scene.paint.{Color => FxColor}
import triangle_tetris.game.board.{Cell, CellIndex}
import triangle_tetris.geometry.Triangle

case class ScreenElement(triangle: Triangle,
                         color: FxColor)

object ScreenElement {
  def apply(cellIndex: CellIndex, cell: Cell): ScreenElement =
    ScreenElement(Triangle(cellIndex, 50), ScreenColor(cell.color))

  def apply(c: (CellIndex, Cell)): ScreenElement =
    apply(c._1, c._2)
}
