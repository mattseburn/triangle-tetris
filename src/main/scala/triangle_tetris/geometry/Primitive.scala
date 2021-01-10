package triangle_tetris.geometry

abstract class Primitive(location: Point) {
  def transpose(delta: Point): Primitive
  def points: List[Point]
}
