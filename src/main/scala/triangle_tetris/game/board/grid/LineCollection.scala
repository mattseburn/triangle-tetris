package triangle_tetris.game.board.grid

case class LineCollection(lines: List[Line] = List()) {
  def ++(lineCollection: LineCollection): LineCollection =
    LineCollection(lines ++ lineCollection.lines)

  def filter(f: Line => Boolean): LineCollection =
    LineCollection(lines.filter(f))

  def partition(f: Line => Boolean): (LineCollection, LineCollection) =
    lines.partition(f) match {
      case (a, b) => (LineCollection(a), LineCollection(b))
    }
}
