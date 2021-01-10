package triangle_tetris.geometry

case class Point(x: Double, y: Double) extends Primitive {
  def ==(p: Point): Boolean =
    p.x == x && p.y == y

  def +(p: Point): Point =
    Point(p.x + x, p.y + y)

  def transpose(delta: Point): Point =
    this + delta

  def points: List[Point] =
    List(this)
}
