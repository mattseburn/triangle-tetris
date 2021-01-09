package triangle_tetris.geometry

abstract class Primitive[P <: Primitive[P]] {
  def transpose(delta: Point): P
}
