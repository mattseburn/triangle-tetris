package triangle_tetris.geometry

case class Line(p1: Point, p2: Point) extends Primitive(location = p1) with Transformations[Line] {
  def ==(l: Line) =
    Set(p1, p2) == Set(l.p1, l.p2)

  def transpose(delta: Point): Line =
    Line(p1 + delta, p2 + delta)

  def rotate(angle: Double): Line =
    Line(p1.rotate(angle), p2.rotate(angle))

  def points: List[Point] =
    List(p1, p2)

  override def toString: String =
    s"Line[$p1, $p2]"
}
