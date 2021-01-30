package triangle_tetris.graphics

import scalafx.scene.paint.{Color => FxColor}
import triangle_tetris.game.board.grid.{Cell, CellIndex}
import triangle_tetris.geometry.Triangle

case class ScreenElement(triangle: Triangle,
                         color: FxColor)

object ScreenElement {
  def apply(cellIndex: CellIndex, cell: Cell, cellMagnitude: Double): ScreenElement =
    ScreenElement(Triangle(cellIndex, cellMagnitude), ScreenColor(cell.color))

  def apply(c: (CellIndex, Cell), cellMagnitude: Double): ScreenElement =
    apply(c._1, c._2, cellMagnitude)
}
