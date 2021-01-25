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
}
