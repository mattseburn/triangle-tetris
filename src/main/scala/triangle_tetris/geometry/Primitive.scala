package triangle_tetris.geometry

abstract class Primitive {
  def transpose(delta: Point): Primitive
  def points: List[Point]
}
