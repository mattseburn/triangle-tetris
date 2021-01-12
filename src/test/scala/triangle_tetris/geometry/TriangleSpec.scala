package triangle_tetris.geometry

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class TriangleSpec extends AnyWordSpec {
  "equality" when {
    "the points are the same" should {
      "return true, regardless of their order" in {
        val p1 = Point(1, 2)
        val p2 = Point(3, 4)
        val p3 = Point(5, 6)

        Triangle(p1, p2, p3) == Triangle(p1, p2, p3) shouldBe true
        Triangle(p1, p2, p3) == Triangle(p3, p2, p1) shouldBe true
      }
    }

    "the points are different" should {
      "return false" in {
        val p1 = Point(1, 2)
        val p2 = Point(3, 4)
        val p3 = Point(5, 6)
        val p4 = Point(7, 8)
        val p5 = Point(9, 10)
        val p6 = Point(11, 12)

        Triangle(p1, p2, p3) == Triangle(p4, p3, p4) shouldBe false
      }
    }
  }

  "transposition" should {
    "add the delta to each point" in {
      val p1 = Point(1, 2)
      val p2 = Point(3, 4)
      val p3 = Point(5, 6)
      val delta = Point(7, 8)
      val t = Triangle(p1, p2, p3)

      t.transpose(delta) shouldBe(Triangle(p1 + delta, p2 + delta, p3 + delta))
    }
  }
}
