package triangle_tetris.geometry

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import scala.math._

class PointSpec extends AnyWordSpec {
  "equality" when {
    "points are the same" should {
      "return true" in {
        val p1 = Point(1, 2)
        val p2 = Point(1, 2)

        p1 == p2 shouldBe true
      }
    }

    "points are different" should {
      "return false" in {
        val p1 = Point(1, 2)
        val p2 = Point(3, 4)

        p1 == p2 shouldBe false
      }
    }
  }

  "unary minus" should {
    "return a new point with the x and y values negated" in {
      val p = Point(1, 2)

      -p shouldEqual Point(-p.x, -p.y)
    }
  }

  "addition" should {
    "return a new point with the coordinates of the two points summed" in {
      val p1 = Point(1, 2)
      val p2 = Point(3, 4)

      p1 + p2 shouldEqual Point(p1.x + p2.x, p1.y + p2.y)
    }
  }

  "subtraction" should {
    "return a new point with the difference between the two points' x and y values" in {
      val p1 = Point(1, 2)
      val p2 = Point(3, 4)

      p1 - p2 shouldEqual Point(p1.x - p2.x, p1.y - p2.y)
    }
  }

  "transposition" should {
    "add the delta point" in {
      val p1 = Point(1, 2)
      val delta = Point(3, 4)

      p1.transpose(delta) shouldEqual p1 + delta
    }
  }

  "rotation" when {
    "rotating about the origin" should {
      "rotate by the given angle" in {
        val p1 = Point(1, 0)
        val angle = toRadians(90)

        p1.rotate(angle) shouldEqual Point(0, 1)
      }
    }

    "rotating about an arbitrary point" should {
      "transpose to the origin before rotating and then back" in {
        val p1 = Point(1, 0)
        val angle = toRadians(90)
        val pivot = Point(1, 1)

        p1.rotate(pivot, angle) shouldEqual Point(2, 1)
      }
    }
  }
}
