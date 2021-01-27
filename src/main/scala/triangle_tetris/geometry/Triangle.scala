package triangle_tetris.geometry

import triangle_tetris.game.board.CellIndex

import scala.math._

case class Triangle(p1: Point, p2: Point, p3: Point) {
  def ==(t: Triangle): Boolean =
    Set(p1, p2, p3) == Set(t.p1, t.p2, t.p3)

  def transpose(delta: Point): Triangle =
    Triangle(p1 + delta, p2 + delta, p3 + delta)

  def points: List[Point] =
    List(p1, p2, p3)

  override def toString: String =
    s"Triangle[$p1, $p2, $p3]"
}

object Triangle {
  def apply(cellIndex: CellIndex, magnitude: Double): Triangle = {
    val triangleHeight = rint(sqrt(pow(magnitude, 2) - pow(magnitude/2, 2)))

    val triangle = cellIndex.i + cellIndex.j + cellIndex.k match {
      case d if d % 2 == 0 =>
        Triangle(
          Point(0, 0),
          Point(triangleHeight, magnitude/2),
          Point(triangleHeight, -magnitude/2))
      case _ =>
        Triangle(
          Point(0, magnitude/2),
          Point(0, -magnitude/2),
          Point(triangleHeight, 0))
    }

    val delta = cellIndex.j + cellIndex.k match {
      case 0 => Point(cellIndex.i * triangleHeight, 0)
      case d if d < 0 =>
        Point(
          cellIndex.i * triangleHeight,
          d*(magnitude/2) - d)
      case d =>
        Point(
          cellIndex.i * triangleHeight,
          d*(magnitude/2) + d)
    }

    triangle.transpose(delta)
  }
}