package triangle_tetris.geometry

import scala.math._

case class Point(x: Double, y: Double) extends Transformations[Point] {
  def ==(p: Point): Boolean =
    p.x == x && p.y == y

  def unary_- : Point =
    Point(-x, -y)

  def +(p: Point): Point =
    Point(p.x + x, p.y + y)

  def -(p: Point): Point =
    Point(p.x - x, p.y - y)

  def *(s: Double): Point =
    Point(x * s, y * s)

  def transpose(delta: Point): Point =
    this + delta

  def rotate(angle: Double): Point =
    Point(
      x * cos(angle) - y * sin(angle),
      x * sin(angle) + y * cos(angle))

  def points: List[Point] =
    List(this)

  override def toString: String =
    s"Point($x, $y)"

  def apply(x: Double, y: Double): Point =
    Point(rint(x), rint(y))
}
