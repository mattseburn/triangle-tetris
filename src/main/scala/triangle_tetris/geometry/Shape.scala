package triangle_tetris.geometry

abstract class Shape[S <: Shape[S]](location: Point) extends Primitive[Shape[S]] {
  def transpose(delta: Point): S
}
