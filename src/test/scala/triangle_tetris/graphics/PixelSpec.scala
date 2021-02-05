package triangle_tetris.graphics

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import triangle_tetris.geometry.Point

class PixelSpec extends AnyWordSpec{
  "apply" when {
    "given a point and screen dimensions" should {
      "translate the point to a pixel" in {
        val p = Point(0, 0)
        val width = 100
        val height = 100

        Pixel(p, width, height) shouldEqual Pixel(50, 50)
      }
    }
  }
}
