package triangle_tetris.geometry

import scala.math._

case class Line(p1: Point, p2: Point) extends Primitive(location = p1) with Transformations[Line] {
  def length: Double =
    rint(sqrt(pow(p2.x - p1.x, 2) + pow(p2.y - p2.y, 2)))

  // intersection of two lines
  def ^(l: Line): Option[Point] = {
    val _x = p1.x - p2.x
    val l_x = l.p1.x - l.p2.x
    val _y = p1.y - p2.y
    val l_y = l.p1.y - l.p2.y
    val a = p1.x * p2.y - p1.y * p2.x;
    val b = l.p1.x * l.p2.y - l.p1.y * l.p2.x;
    val c = _x * l_x - _y * l_y

    if (c == 0) { None }
    else {
      val x = (a * l_x - b * _x) / c
      val y = (a * l_y - b * _y) / c

      if (
        ((p1.x <= x && x <= p2.x) || (p2.x <= x && x <= p2.x)) &&
        ((p1.y <= y && y <= p2.y) || (p2.y <= y && y <= p2.y))
      ) { Some(Point(x, y)) }
      else { None }
    }
  }

  def ==(l: Line): Boolean =
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

object Line {
  def apply(p1: Point, p2: Point): Line = {
    if (p1 == p2)
      throw new IllegalArgumentException(s"Points must be different: $p1, $p2")

    new Line(p1, p2)
  }
}
