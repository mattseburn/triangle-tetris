package triangle_tetris.game.board.grid

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import triangle_tetris.game.board.grid.Axis._

class CellIndexSpec extends AnyWordSpec{
  "unary minus" should {
    "negate each value" in {
      -CellIndex(1, 2, 3) shouldEqual CellIndex(-1, -2, -3)
    }
  }

  "addition" should {
    "add the values" in {
      val c1 = CellIndex(1, 2, 3)
      val c2 = CellIndex(4, 5, 6)

      c1 + c2 shouldEqual CellIndex(c1.i + c2.i, c1.j + c2.j, c1.k + c2.k)
    }
  }

  "equality" when {
    "the indexes are the same" should {
      "return true" in {
        val c1 = CellIndex(1, 2, 3)

        c1 == c1 shouldBe true
      }
    }

    "the indexes are different" should {
      "return false" in {
        val c1 = CellIndex(1, 2, 3)
        val c2 = CellIndex(4, 5, 6)

        c1 == c2 shouldBe false
      }
    }
  }

  "indexValue" should {
    "return the correct index" in {
      val c = CellIndex(1, 2, 3)

      c.indexValue(I) shouldEqual c.i
      c.indexValue(J) shouldEqual c.j
      c.indexValue(K) shouldEqual c.k
    }
  }
}
