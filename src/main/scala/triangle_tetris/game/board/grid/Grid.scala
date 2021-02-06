package triangle_tetris.game.board.grid

import scala.math._

case class Grid(cells: Map[CellIndex, Cell]) {
  def update(cellIndex: CellIndex, cell: Cell): Grid =
    Grid(cells ++ Map(cellIndex -> cell))

  def update(newCells: Map[CellIndex, Cell]): Grid =
    Grid(cells ++ newCells)

  def contains(cellIndex: CellIndex): Boolean =
    cells.contains(cellIndex)

  def contains(cellIndexes: List[CellIndex]): Boolean =
    cellIndexes.forall(contains)

  def occupied(cellIndex: CellIndex): Boolean =
    if (!contains(cellIndex)) { throw new IndexOutOfBoundsException(s"Invalid cell index: $cellIndex") }
    else cells
      .get(cellIndex)
      .exists(_.color.isDefined)

  def occupied(cellIndexes: List[CellIndex]): Boolean =
    cellIndexes.forall(occupied)

  def empty(cellIndex: CellIndex): Boolean =
    !occupied(cellIndex)

  def empty(cellIndexes: List[CellIndex]): Boolean =
    cellIndexes.forall(empty)

  def columnHeads: List[CellIndex] =
    cells.keys.toList
      .groupBy(_.i).toList
      .map(_._2.sortWith((a, b) => a.j >= b.j && a.k >= b.k).head)
      .sortBy(_.i)

  override def toString: String =
    cells.groupBy(c => c._1.j + c._1.k).toList
      .sortBy(_._1).reverse
      .map(_._2.keys.toList.sortBy(_.i))
      .map(_.mkString(", "))
      .mkString("\n")
}

object Grid {
  def apply(width: Int, height: Int): Grid =
    Grid((-width / 2 until width / 2).toList
      .flatMap(i => {
        val midJ = floor(i.toDouble / 2).toInt
        val minJ = midJ - height / 2
        val maxJ = midJ + height / 2

        val midK = ceil(-i.toDouble / 2).toInt
        val minK = midK - height / 2
        val maxK = midK + height / 2

        val jRange = (minJ until maxJ).toList
        val kRange = (minK until maxK).toList

        (jRange zip kRange flatMap {
          case (j, k) if i % 2 == 0 => List(
            CellIndex(i, j, k + 1),
            CellIndex(i, j, k))
          case (j, k) => List(
            CellIndex(i, j + 1, k),
            CellIndex(i, j, k))
        }) ++ List(CellIndex(i, maxJ, maxK))
      }))

  def apply(cellIndexes: List[CellIndex]): Grid =
    Grid(cellIndexes.map((_, Cell())).toMap)
}
