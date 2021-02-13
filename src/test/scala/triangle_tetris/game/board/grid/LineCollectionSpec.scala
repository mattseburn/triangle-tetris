package triangle_tetris.game.board.grid

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class LineCollectionSpec extends AnyWordSpec {
  "concatenation" should {
    "return a new concatenated line collection" in {
      val l1 = Line(Map(
        CellIndex(1, 2, 3) -> Cell(),
        CellIndex(1, 4, 5) -> Cell(),
        CellIndex(1, 8, 7) -> Cell()))
      val l2 = Line(Map(
        CellIndex(1, 2, 3) -> Cell(),
        CellIndex(4, 2, 5) -> Cell(),
        CellIndex(6, 2, 7) -> Cell()))

      LineCollection(List(l1)) ++ LineCollection(List(l2)) shouldEqual LineCollection(List(l1, l2))
    }
  }

  "filter" should {
    "return a new filtered line collection" in {
      val l1 = Line(Map(
        CellIndex(1, 2, 3) -> Cell(),
        CellIndex(1, 4, 5) -> Cell(),
        CellIndex(1, 8, 7) -> Cell()))
      val l2 = Line(Map(
        CellIndex(1, 6, 7) -> Cell(),
        CellIndex(1, 5, 4) -> Cell(),
        CellIndex(1, 3, 2) -> Cell()))

      LineCollection(List(l1, l2))
        .filter(_.cells.keys.toList.contains(l1.cells.head._1)) shouldEqual LineCollection(List(l1))
    }
  }

  "partition" should {
    "return two new partitioned line collections" in {
      val l1 = Line(Map(
        CellIndex(1, 2, 3) -> Cell(),
        CellIndex(1, 4, 5) -> Cell(),
        CellIndex(1, 8, 7) -> Cell()))
      val l2 = Line(Map(
        CellIndex(1, 6, 7) -> Cell(),
        CellIndex(1, 5, 4) -> Cell(),
        CellIndex(1, 3, 2) -> Cell()))

      LineCollection(List(l1, l2))
        .partition(_.cells.keys.toList.contains(l1.cells.head._1)) shouldEqual
        (LineCollection(List(l1)), LineCollection(List(l2)))
    }
  }
}
