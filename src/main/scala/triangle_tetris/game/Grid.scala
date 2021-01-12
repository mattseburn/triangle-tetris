package triangle_tetris.game

import triangle_tetris.geometry.{Line, Point, Triangle}

case class Grid(gridWidth: Double, gridHeight: Double, cellMagnitude: Double)
  extends GameElement[Line] {

  import GameElement._

  def cellHeight: Double = triangleHeight(cellMagnitude)
  def width: Double = cellHeight * gridWidth
  def height: Double = cellMagnitude * gridHeight

  def children: List[Line] = lines
  def lines: List[Line] = verticalLines

  private def verticalLines: List[Line] =
    (-(width/2).toInt to (width/2).toInt by cellHeight.toInt).map {
      case i if i % 2 == 0 =>
        println(s"$i")
        Line(Point(i, height/2 - cellHeight), Point(i, -height/2 + cellHeight))
      case i =>
        println(s"$i")
        Line(Point(i, height/2), Point(i, -height/2))
    }.toList

  private def diagonalLines: List[Line] = ???
}
