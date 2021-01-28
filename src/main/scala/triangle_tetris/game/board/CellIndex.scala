package triangle_tetris.game.board

case class CellIndex(i: Int, j: Int, k: Int) {
  def +(c: CellIndex): CellIndex =
    CellIndex(c.i + i, c.j + j, c.k + k)

  def ==(c: CellIndex): Boolean =
    i == c.i && j == c.j && k == c.k

  override def toString: String =
    s"($i, $j, $k)"
}

object CellIndex {
  implicit val order: Ordering[CellIndex] =
    Ordering.by(unapply)

  def apply(direction: Direction): CellIndex = direction match {
    case Direction.Right => CellIndex(1, 0, -1)
    case Direction.Left => CellIndex(-1, -1, 0)
    case Direction.Down => CellIndex(0, -1, -1)
  }
}
