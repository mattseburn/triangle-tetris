package triangle_tetris.geometry

case class Line(p1: Point, p2: Point) {
  def ==(l: Line) =
    l.p1 == p1 && l.p2 == p2
}
