package triangle_tetris.game.board

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import triangle_tetris.game.Color
import triangle_tetris.game.board.grid.{Cell, CellIndex, Grid}

import scala.util.Random

class GridSpec extends AnyWordSpec {
  val gridIndexes = List(
    CellIndex(0, 0, 0),
    CellIndex(0, 0, 1),
    CellIndex(0, 1, 0),
    CellIndex(1, 1, 0))
  val nonGridIndexes = List(
    CellIndex(1, 1, 1),
    CellIndex(2, 2, 2),
    CellIndex(3, 3, 3),
    CellIndex(4, 4, 4))

  "contains" when {
    val grid = Grid(gridIndexes)

    "given a single index" when {
      "the index exists on the grid" should {
        "return true" in {
          grid.contains(gridIndexes(Random.nextInt(gridIndexes.length))) shouldBe true
        }
      }

      "the index does not exist on the grid" should {
        "return false" in {
          grid.contains(CellIndex(-1, -1, -1)) shouldBe false
        }
      }
    }

    "given a list of indexes" when {
      "of the indexes exist on the grid" should {
        "return true" in {
          grid.contains(gridIndexes) shouldBe true
        }
      }

      "none of the indexes exist on the grid" should {
        "return false" in {
          grid.contains(nonGridIndexes) shouldBe false
        }
      }
    }
  }

  "occupied" when {
    val grid = Grid(gridIndexes.zipWithIndex.map {
      case (index, i) if i % 2 == 0 => (index, Cell(Some(Color.Red)))
      case (index, _) => (index, Cell())
    }.toMap)

    "given a single index" when {
      "the index exists on the grid" when {
        "the cell is occupied" should {
          "return true" in {
            grid.occupied(gridIndexes.head) shouldBe true
          }
        }

        "the cell is not occupied" should {
          "return false" in {
            grid.occupied(gridIndexes.tail) shouldBe false
          }
        }
      }

      "the index doesn't exist on the grid" should {
        "throw an exception" in {
          assertThrows[IndexOutOfBoundsException] { grid.occupied(nonGridIndexes.head) }
        }
      }
    }

    "given a list of indexes" when {
      "the indexes all exist on the grid" when {
        "the cells are all occupied" should {
          "return true" in {
            grid.occupied(gridIndexes.zipWithIndex.filter {
              case (_, i) if i % 2 == 0 => true
              case _ => false
            }.map(_._1)) shouldBe true
          }
        }

        "none of the cell are occupied" should {
          "return false" in {
            grid.occupied(gridIndexes.zipWithIndex.filter {
              case (_, i) if i % 2 == 0 => false
              case _ => true
            }.map(_._1)) shouldBe false
          }
        }
      }

      "none of the indexes exist on the grid" should {
        "throw an exception" in {
          assertThrows[IndexOutOfBoundsException] { grid.occupied(nonGridIndexes) }
        }
      }
    }
  }

  "empty" when {
    val grid = Grid(gridIndexes.zipWithIndex.map {
      case (index, i) if i % 2 == 0 => (index, Cell())
      case (index, _) => (index, Cell(Some(Color.Red)))
    }.toMap)

    "given a single index" when {
      "the index exists on the grid" when {
        "the cell is empty" should {
          "return true" in {
            grid.empty(gridIndexes.head) shouldBe true
          }
        }

        "the cell is not empty" should {
          "return false" in {
            grid.empty(gridIndexes.tail) shouldBe false
          }
        }
      }

      "the index doesn't exist on the grid" should {
        "throw an exception" in {
          assertThrows[IndexOutOfBoundsException] { grid.empty(nonGridIndexes.head) }
        }
      }
    }

    "given a list of indexes" when {
      "the indexes all exist on the grid" when {
        "the cells are all empty" should {
          "return true" in {
            grid.empty(gridIndexes.zipWithIndex.filter {
              case (_, i) if i % 2 == 0 => true
              case _ => false
            }.map(_._1)) shouldBe true
          }
        }

        "none of the cell are empty" should {
          "return false" in {
            grid.empty(gridIndexes.zipWithIndex.filter {
              case (_, i) if i % 2 == 0 => false
              case _ => true
            }.map(_._1)) shouldBe false
          }
        }
      }

      "none of the indexes exist on the grid" should {
        "throw an exception" in {
          assertThrows[IndexOutOfBoundsException] { grid.empty(nonGridIndexes) }
        }
      }
    }
  }

  "apply" when {
    "given a width and height" should {
      "should create a new grid with the correct indexes" in {
        val width = 2
        val height = 2
        val grid = Grid(width, height)
        val expectedIndexes = List(
          CellIndex(-1, -2, 0),
          CellIndex(0, 0, 1),
          CellIndex(-1, 0, 2),
          CellIndex(0, -1, 0),
          CellIndex(-1, -1, 1),
          CellIndex(-1, 0, 1),
          CellIndex(0, 1, 1),
          CellIndex(0, -1, -1),
          CellIndex(-1, -1, 0),
          CellIndex(0, 0, 0))
        val expectedCells = expectedIndexes.map((_, Cell())).toMap

        grid.cells shouldEqual expectedCells
      }
    }
  }
}
