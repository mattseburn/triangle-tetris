package triangle_tetris.geometry

import scala.math._

case class Line(p1: Point, p2: Point) extends Primitive(location = p1) with Transformations[Line] {
  def length =
    rint(sqrt(pow(p2.x - p1.x, 2) + pow(p2.y - p2.y, 2)))

  def ==(l: Line) =
    Set(p1, p2) == Set(l.p1, l.p2)

  def transpose(delta: Point): Line =
    Line(p1 + delta, p2 + delta)

  def rotate(angle: Double): Line =
    Line(p1.rotate(angle), p2.rotate(angle))

  def resize(size: Double): Line =
    Line(p1, p2 * (size / length))

  def points: List[Point] =
    List(p1, p2)

  override def toString: String =
    s"Line[$p1, $p2]"
}
