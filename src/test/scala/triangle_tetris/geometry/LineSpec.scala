package triangle_tetris.geometry

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class LineSpec extends AnyWordSpec {
  "equality" when {
    "endpoints are the same" should {
      "return true, regardless of their order" in {
        val p1 = Point(1, 2)
        val p2 = Point(3, 4)

        Line(p1, p2) == Line(p1, p2) shouldBe true
        Line(p1, p2) == Line(p2, p1) shouldBe true
      }
    }

    "one endpoint is the same" should {
      "return false" in {
        val p1 = Point(1, 2)
        val p2 = Point(3, 4)
        val p3 = Point(5, 6)

        Line(p1, p2) == Line(p1, p3) shouldBe false
      }
    }

    "both endpoints are different" should {
      "return false" in {
        val p1 = Point(1, 2)
        val p2 = Point(3, 4)
        val p3 = Point(5, 6)
        val p4 = Point(7, 8)

        Line(p1, p2) == Line(p3, p4) shouldBe false
      }
    }
  }

  "transposition" should {
    "add the delta to each endpoint" in {
      val p1 = Point(1, 2)
      val p2 = Point(3, 4)
      val delta = Point(5, 6)
      val l = Line(p1, p2)

      l.transpose(delta) shouldBe(Line(p1 + delta, p2 + delta))
    }
  }
}
