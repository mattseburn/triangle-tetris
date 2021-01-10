package triangle_tetris.geometry

case class Triangle(p1: Point, p2: Point, p3: Point)extends Primitive(location = p1) with Transformations[Triangle] {
  def ==(t: Triangle): Boolean =
    Set(p1, p2, p3) == Set(t.p1, t.p2, t.p3)

  def transpose(delta: Point): Triangle =
    Triangle(p1 + delta, p2 + delta, p3 + delta)

  def rotate(angle: Double): Triangle =
    Triangle(p1.rotate(angle), p2.rotate(angle), p3.rotate(angle))

  def points: List[Point] =
    List(p1, p2, p3)

  override def toString: String =
    s"Triangle[$p1, $p2, $p3]"
}
