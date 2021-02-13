package triangle_tetris.game.board.grid

import triangle_tetris.game.board.grid.Axis._

case class Line(cells: Map[CellIndex, Cell]) {
  def ==(l: Line): Boolean =
    cells == l.cells

  def >(l: Line): Boolean =
    axis == l.axis & cells.head._1.indexValue(axis) > l.cells.head._1.indexValue(l.axis)

  def <(l: Line): Boolean =
    axis == l.axis & cells.head._1.indexValue(axis) < l.cells.head._1.indexValue(l.axis)

  def isComplete: Boolean =
    cells.toList
      .forall(_._2.color.isDefined)

  def isEmpty: Boolean =
    cells.toList
      .forall(_._2.color.isEmpty)

  def axis: Axis =
    if (isOnAxis(I)) I
    else if (isOnAxis(J)) J
    else if (isOnAxis(K)) K
    else throw new Exception(s"Somehow this line isn't on any axis: $this")

  private def isOnAxis(axis: Axis): Boolean =
    cells.forall(_._1.indexValue(axis) == cells.head._1.indexValue(axis))

  override def toString: String =
    s"Line[${cells.mkString(", ")}]"
}

object Line {
  def apply(cells: Map[CellIndex, Cell]): Line = {
    if (isLine(cells)) { new Line(cells) }
    else { throw new IllegalArgumentException(s"Cells do not describe a line") }
  }

  private def isLine(cells: Map[CellIndex, Cell]): Boolean =
    isLineByAxis(cells, I) || isLineByAxis(cells, J) || isLineByAxis(cells, K)

  private def isLineByAxis(cells: Map[CellIndex, Cell], axis: Axis): Boolean =
    cells.size > 2 && cells.forall(_._1.indexValue(axis) == cells.head._1.indexValue(axis))
}

/*

  - Axis(line)
  - line.axis

 */
