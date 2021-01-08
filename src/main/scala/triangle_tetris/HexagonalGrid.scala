package triangle_tetris

import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color._
import triangle_tetris.geometry.{Line, Point}

import scala.math._

case class HexagonalGrid(gridWidth: Double, gridHeight: Double, cellMagnitude: Double) {
  private val gridPadding = 50d

  private def cellHeight =
    rint(sqrt(pow(cellMagnitude, 2) - pow(cellMagnitude / 2, 2)))

  private def verticalLines: List[Line] =
    (0 to gridWidth.toInt).map {
      case i if i % 2 == 0 =>
        Line(Point(i * cellHeight, rint(cellMagnitude/2)), Point(i * cellHeight, gridHeight * cellMagnitude - (cellMagnitude/2)))
      case i =>
        Line(Point(i * cellHeight, 0d), Point(i * cellHeight, gridHeight * cellMagnitude))
    }.toList

  private def diagonalLines: List[Line] =
    (((cellMagnitude/2).toInt to (cellMagnitude * gridWidth).toInt by cellMagnitude.toInt).zipWithIndex.map {
      case (y, i) => List(
        Line(Point(0d, y.toDouble), Point(2*i*cellHeight + cellHeight, 0d)),
        Line(Point(0d, y.toDouble), Point(gridWidth * cellHeight, (gridWidth/2 + i) * cellMagnitude + cellMagnitude/2)),
      )
    }.toList ++
    (cellHeight.toInt to (gridWidth * cellHeight).toInt by cellHeight.toInt * 2).zipWithIndex.map {
      case (x, i) => List(
        Line(
          Point(x.toDouble, 0d),
          Point(gridWidth * cellHeight, (gridWidth/2 - i - 1) * cellMagnitude + cellMagnitude/2)
        ),
        Line(
          Point(x.toDouble, gridHeight*cellMagnitude),
          Point(gridWidth * cellHeight, gridHeight*cellMagnitude - (gridWidth/2 - i - 1) * cellMagnitude - cellMagnitude/2)
        )
      )
    }.toList).flatten

  private def hexGridLines: List[Line] =
    verticalLines ++ diagonalLines

  def render: Scene = new Scene {
    fill = Black
    content = new Pane {
      padding = Insets(gridPadding)
      children = List(new Canvas(gridWidth * cellHeight, gridHeight * cellMagnitude) {
        graphicsContext2D.lineWidth = 1

        graphicsContext2D.stroke = White
        hexGridLines.foreach { l => graphicsContext2D.strokeLine(l.p1.x, l.p1.y, l.p2.x, l.p2.y) }

        translateX = gridPadding
        translateY = gridPadding
      })
    }
  }
}
