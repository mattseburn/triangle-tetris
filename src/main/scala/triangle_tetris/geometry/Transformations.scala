package triangle_tetris.geometry

trait Transformations[T <: Transformations[T]] {
  def rotate(pivot: Point, angle: Double): T =
    this
      .transpose(-pivot)
      .rotate(angle)
      .transpose(pivot)

  def rotate(angle: Double): T
  def transpose(delta: Point): T
}
