package triangle_tetris.geometry

import scala.math._

case class Point(x: Double, y: Double) {
  def ==(p: Point): Boolean =
    p.x == x && p.y == y

  def unary_- : Point =
    Point(-x, -y)

  def +(p: Point): Point =
    Point(p.x + x, p.y + y)

  def -(p: Point): Point =
    Point(x - p.x, y - p.y)

  def *(s: Double): Point =
    Point(x * s, y * s)

  override def toString: String =
    s"Point($x, $y)"

  def apply(x: Double, y: Double): Point =
    Point(rint(x), rint(y))
}
