package triangle_tetris.screen

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import triangle_tetris.game.board.grid.{Cell, CellIndex}
import triangle_tetris.geometry.{Point, Triangle}

class ScreenElementSpec extends AnyWordSpec{
  "apply" when {
    "given a cell and magnitude" should {
      "convert it to a screen element" in {
        val c = CellIndex(0, 0, 0)
        val m = 10
        val expectedTriangle = Triangle(Point(0, 0), Point(9, 5), Point(9, -5))

        ScreenElement(c, Cell(), m) shouldEqual ScreenElement(expectedTriangle, ScreenColor(None))
      }
    }
  }
}
