package triangle_tetris.geometry

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class LineSpec extends AnyWordSpec {
  "creation" when {
    "endpoints are the same" should {
      "throw an IllegalArgumentException" in {
        val p1 = Point(1, 2)

        assertThrows[IllegalArgumentException] { Line(p1, p1) }
      }
    }
  }

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

      l.transpose(delta) shouldBe Line(p1 + delta, p2 + delta)
    }
  }

  "intersection" when {
    "lines are parallel" should {
      "return None" in {
        val l1 = Line(Point(-1, -1), Point(1, 1))
        val l2 = Line(Point(-1, 0), Point(1, 2))

        l1 ^ l2 shouldBe None
      }
    }

    "lines intersect" should {
      "return intersection point" in {
        val l1 = Line(Point(-1, -1), Point(1, 1))
        val l2 = Line(Point(-1, 1), Point(1, -1))

        l1 ^ l2 shouldEqual Some(Point(0, 0))
      }
    }

    "one of the lines is horizontal" should {
      "return intersection point" in {
        val l1 = Line(Point(-2, 1), Point(2, 1))
        val l2 = Line(Point(-2, -2), Point(2, -2))

        l1 ^ l2 shouldEqual Some(Point(1, 1))
      }
    }

    "one of the lines is vertical" should {
      "return intersection point" in {
        val l1 = Line(Point(1, -2), Point(1, 2))
        val l2 = Line(Point(-2, -2), Point(2, -2))

        l1 ^ l2 shouldEqual Some(Point(1, 1))
      }
    }
  }

  "contains" when {
    "the point is on the line segment" should {
      "return true" in {
        val l = Line(Point(-1, -1), Point(1, 1))
        val p = Point(0, 0)

        l.contains(p) shouldBe true
      }
    }

    "the point is not on the line segment" should {
      "return false" in {
        val l = Line(Point(-1, -1), Point(1, 1))
        val p = Point(2, 2)

        l.contains(p) shouldBe false
      }
    }
  }
}
