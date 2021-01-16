package triangle_tetris.graphics

import triangle_tetris.geometry.Point

case class Pixel(x: Double, y: Double)

object Pixel {
  def apply(p: Point, screenWidth: Double, screenHeight: Double): Pixel = {
    Pixel(p.x + screenWidth/2, -(p.y - screenHeight/2))
  }
}
