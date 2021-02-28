package triangle_tetris.game.board.grid

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import triangle_tetris.game.pieces.Color._
import triangle_tetris.game.board.grid.Axis._

class LineSpec extends AnyWordSpec {
  "apply" when {
    "not given enough cells for a line" should {
      "raise an error" in {
        val cells = Map(
          CellIndex(0, 0, 0) -> Cell(),
          CellIndex(0, 0, 1) -> Cell())

        assertThrows[IllegalArgumentException] { Line(cells) }
      }
    }

    "cells are not in a line" should {
      "raise an error" in {
        val cells = Map(
          CellIndex(1, 2, 3) -> Cell(),
          CellIndex(4, 5, 6) -> Cell(),
          CellIndex(7, 8, 9) -> Cell())

        assertThrows[IllegalArgumentException] { Line(cells) }
      }
    }

    "cells do describe a line" should {
      "return a new Line" in {
        val cells = Map(
          CellIndex(1, 2, 3) -> Cell(),
          CellIndex(1, 4, 5) -> Cell(),
          CellIndex(1, 8, 7) -> Cell())

        Line(cells) shouldBe a[Line]
      }
    }
  }

  "isComplete" when {
    "all cells in the line are occupied" should {
      "return true" in {
        val cells = Map(
          CellIndex(1, 2, 3) -> Cell(Some(Red)),
          CellIndex(1, 4, 5) -> Cell(Some(Red)),
          CellIndex(1, 8, 7) -> Cell(Some(Red)))

        Line(cells).isComplete shouldBe true
      }
    }

    "not all of the cells in the line are occupied" should {
      "return false" in {
        val cells = Map(
          CellIndex(1, 2, 3) -> Cell(),
          CellIndex(1, 4, 5) -> Cell(Some(Red)),
          CellIndex(1, 8, 7) -> Cell(Some(Red)))

        Line(cells).isComplete shouldBe false
      }
    }
  }

  "isEmpty" when {
    "all cells in the line are empty" should {
      "return true" in {
        val cells = Map(
          CellIndex(1, 2, 3) -> Cell(),
          CellIndex(1, 4, 5) -> Cell(),
          CellIndex(1, 8, 7) -> Cell())

        Line(cells).isEmpty shouldBe true
      }
    }

    "not all of the cells in the line are empty" should {
      "return false" in {
        val cells = Map(
          CellIndex(1, 2, 3) -> Cell(),
          CellIndex(1, 4, 5) -> Cell(Some(Red)),
          CellIndex(1, 8, 7) -> Cell())

        Line(cells).isEmpty shouldBe false
      }
    }
  }

  "axis" should {
    "return the correct axis" in {
      val iLine = Line(Map(
        CellIndex(1, 2, 3) -> Cell(),
        CellIndex(1, 4, 5) -> Cell(),
        CellIndex(1, 8, 7) -> Cell()))
      val jLine = Line(Map(
        CellIndex(1, 2, 3) -> Cell(),
        CellIndex(4, 2, 5) -> Cell(),
        CellIndex(6, 2, 7) -> Cell()))
      val kLine = Line(Map(
        CellIndex(1, 2, 3) -> Cell(),
        CellIndex(4, 5, 3) -> Cell(),
        CellIndex(6, 7, 3) -> Cell()))

      iLine.axis shouldBe I
      jLine.axis shouldBe J
      kLine.axis shouldBe K
    }
  }

  "equality" when {
    "the lines are the same" should {
      "return true" in {
        val cells = Map(
            CellIndex(1, 2, 3) -> Cell(),
            CellIndex(1, 4, 5) -> Cell(),
            CellIndex(1, 8, 7) -> Cell())
        val l1 = Line(cells)
        val l2 = Line(cells)

        l1 == l2 shouldBe true
      }
    }

    "the lines are different" should {
      "return false" in {
        val l1 = Line(Map(
          CellIndex(1, 2, 3) -> Cell(),
          CellIndex(1, 4, 5) -> Cell(),
          CellIndex(1, 8, 7) -> Cell()))
        val l2 = Line(Map(
          CellIndex(1, 7, 6) -> Cell(),
          CellIndex(1, 5, 4) -> Cell(),
          CellIndex(1, 3, 2) -> Cell()))

        l1 == l2 shouldBe false
      }
    }
  }

  "greater than" when {
    "the given line is above this one" should {
      "return true" in {
        val l1 = Line(Map(
          CellIndex(2, 2, 3) -> Cell(),
          CellIndex(2, 4, 5) -> Cell(),
          CellIndex(2, 8, 7) -> Cell()))
        val l2 = Line(Map(
          CellIndex(1, 7, 6) -> Cell(),
          CellIndex(1, 5, 4) -> Cell(),
          CellIndex(1, 3, 2) -> Cell()))

        l1 > l2 shouldBe true
      }
    }

    "the given line is below this one" should {
      "return false" in {
        val l1 = Line(Map(
          CellIndex(1, 2, 3) -> Cell(),
          CellIndex(1, 4, 5) -> Cell(),
          CellIndex(1, 8, 7) -> Cell()))
        val l2 = Line(Map(
          CellIndex(2, 7, 6) -> Cell(),
          CellIndex(2, 5, 4) -> Cell(),
          CellIndex(2, 3, 2) -> Cell()))

        l1 > l2 shouldBe false
      }
    }
  }

  "less than" when {
    "the given line is below this one" should {
      "return true" in {
        val l1 = Line(Map(
          CellIndex(1, 2, 3) -> Cell(),
          CellIndex(1, 4, 5) -> Cell(),
          CellIndex(1, 8, 7) -> Cell()))
        val l2 = Line(Map(
          CellIndex(2, 7, 6) -> Cell(),
          CellIndex(2, 5, 4) -> Cell(),
          CellIndex(2, 3, 2) -> Cell()))

        l1 < l2 shouldBe true
      }
    }

    "the given line is above this one" should {
      "return false" in {
        val l1 = Line(Map(
          CellIndex(2, 2, 3) -> Cell(),
          CellIndex(2, 4, 5) -> Cell(),
          CellIndex(2, 8, 7) -> Cell()))
        val l2 = Line(Map(
          CellIndex(1, 7, 6) -> Cell(),
          CellIndex(1, 5, 4) -> Cell(),
          CellIndex(1, 3, 2) -> Cell()))

        l1 < l2 shouldBe false
      }
    }
  }
}
