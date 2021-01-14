package triangle_tetris.geometry

import scala.math._

case class Line(p1: Point, p2: Point) extends Primitive(location = p1) with Transformations[Line] {
  def length: Double =
    rint(sqrt(pow(p2.x - p1.x, 2) + pow(p2.y - p2.y, 2)))

  // (permissive) intersection of two lines
  def ^(l: Line): Option[Point] = {
    val x1 = p1.x
    val y1 = p1.y
    val x2 = p2.x
    val y2 = p2.y
    val x3 = l.p1.x
    val y3 = l.p1.y
    val x4 = l.p2.x
    val y4 = l.p2.y

    val a = x1*y2 - y1*x2
    val b = x3*y4 - y3*x4
    val c = (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4)

    println(s"intersection of $this and $l")

    c match {
      case 0 => None
      case _ =>
        val x = (a * (x3 - x4) - b * (x1 - x2)) / c
        val y = (a * (y3 - y4) - b * (y1 - y2)) / c

        val p = Point(x, y)

        println(s"result: $p")

        Some(p)
    }
  }

  def contains(p: Point): Boolean =
    p == p1 || p == p2 || Line(p1, p).length == Line(p, p2).length

  def ==(l: Line): Boolean =
    Set(p1, p2) == Set(l.p1, l.p2)

  def transpose(delta: Point): Line = {
    val l = Line(p1 + delta, p2 + delta)
    println(s"transposing $this by $delta, result: $l")
    l
  }

  def rotate(angle: Double): Line = {
    val l =Line(p1.rotate(angle), p2.rotate(angle))
    println(s"rotating $this by $angle, result: $l")
    l
  }

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
