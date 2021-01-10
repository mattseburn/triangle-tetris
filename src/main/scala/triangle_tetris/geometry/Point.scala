package triangle_tetris.geometry

case class Point(x: Double, y: Double) {
  def ==(p: Point): Boolean =
    p.x == x && p.y == y

  def +(p: Point): Point =
    Point(p.x + x, p.y + y)

  def transpose(delta: Point): Point =
    this + delta

  def points: List[Point] =
    List(this)

  override def toString: String =
    s"Point($x, $y)"
}
