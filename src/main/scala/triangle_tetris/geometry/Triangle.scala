package triangle_tetris.geometry

case class Triangle(p1: Point, p2: Point, p3: Point) extends Shape(location = p1) {
  def ==(t: Triangle): Boolean =
    Set(p1, p2, p3) == Set(t.p1, t.p2, t.p3)

  def transpose(delta: Point): Triangle =
    Triangle(p1 + delta, p2 + delta, p3 + delta)

  def points: List[Point] =
    List(p1, p2, p3)
}
