package triangle_tetris.geometry

case class Line(p1: Point, p2: Point) extends Primitive[Line] {
  def ==(l: Line) =
    Set(p1, p2) == Set(l.p1, l.p2)

  def transpose(delta: Point): Line =
    Line(p1 + delta, p2 + delta)
}
