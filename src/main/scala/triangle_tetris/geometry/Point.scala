package triangle_tetris.geometry

/*

  - coordinate system
  - default is (0, 0) in bottom left
  - elements in top right quadrant of graph
  - rendering system has (0, 0) in top left
  - elements in only quadrant
  - convert by transposing down by the height of the canvas, and multiplying y by -1

  - should each type of element convert itself?
  - by converting its children?

 */

case class Point(x: Double, y: Double) extends Primitive[Point] {
  def ==(p: Point): Boolean =
    p.x == x && p.y == y

  def +(p: Point): Point =
    Point(p.x + x, p.y + y)

  def transpose(delta: Point): Point =
    this + delta
}
