package triangle_tetris.geometry

case class Point(x: Double, y: Double) {
  def ==(p: Point): Boolean =
    p.x == x && p.y == y
}
