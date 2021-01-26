package triangle_tetris.game.board

import scala.collection.SortedMap

import scala.math._

case class Grid(cells: SortedMap[CellIndex, Cell]) {
  def update(cellIndex: CellIndex, cell: Cell): Grid =
    Grid(cells ++ SortedMap(cellIndex -> cell))

  def update(newCells: Map[CellIndex, Cell]): Grid =
    Grid(cells ++ newCells)

  def contains(cellIndex: CellIndex): Boolean =
    cells.contains(cellIndex)

  def occupied(cellIndex: CellIndex): Boolean =
    contains(cellIndex) && cells
      .get(cellIndex)
      .exists(_.color.isDefined)

  def empty(cellIndex: CellIndex): Boolean =
    !occupied(cellIndex)

  override def toString: String =
    cells.toList.map {
      case (index, cell) => s"$index, $cell"
    }.mkString("\n")
}

object Grid {
  def apply(width: Int, height: Int): Grid =
    new Grid(SortedMap[CellIndex, Cell]() ++ (-width/2 until width/2).toList
      .flatMap(i => {
        val midJK = (i match {
          case _i if _i > 0 => floor(i/2)
          case _ => floor((i - 1)/2)
        }).toInt
        val minJK = midJK - height/2
        val maxJK = midJK + height/2
        val (range, endIndex) = i match {
          case _i if _i % 2 == 0 =>
            ((minJK to maxJK - 1).toList,
              CellIndex(i, minJK, maxJK))
          case _ =>
            ((minJK + 1 to maxJK).toList,
              CellIndex(i, maxJK, minJK))
        }

        (List(endIndex) ++ range.flatMap(k =>
          List(
            CellIndex(i, i - k, k),
            CellIndex(i, i - k - 1, k))))
          .map(index => (index, Cell()))
      }).toMap)
}
