package triangle_tetris.game.board.movement

import triangle_tetris.game.board.grid.CellIndex
import RotationalDirection._

case class Step(value: Int = 0) {
  def prev: Step =
    this.copy(value = (value - 1) % 6)

  def next: Step =
    this.copy(value = (value + 1) % 6)

  def translateIndex(i: Int): Int =
    (value + i) % 6 match {
      case _i if _i < 0 => _i + 6
      case _i => _i
    }
}

case class Rotation(private val step: Step = Step()) {

  def update(rotationalDirection: RotationalDirection): Rotation = rotationalDirection match {
    case Clockwise => this.copy(step = step.prev)
    case Counterclockwise => this.copy(step = step.next)
  }

  def rotateCell(cellIndex: CellIndex): CellIndex =
    indexOrders
      .flatMap(indexes => indexes.zipWithIndex
        .find(_._1 == cellIndex)
        .map {
          case (_, i) => indexes(step.translateIndex(i))
        })
      .headOption
      .getOrElse(throw new IllegalArgumentException(s"Invalid cell index for rotation: $cellIndex"))

  private val indexOrders: List[List[CellIndex]] = List(
    List(
      CellIndex(0, 0, 0),
      CellIndex(0, 0, 1),
      CellIndex(-1, 0, 1),
      CellIndex(-1, -1, 1),
      CellIndex(-1, -1, 0),
      CellIndex(0, -1, 0)),
    List(
      CellIndex(1, 0, -1),
      CellIndex(1, 1, 1),
      CellIndex(-1, 1, 2),
      CellIndex(-2, -1, 2),
      CellIndex(-2, -2, 0),
      CellIndex(0, -2, -1)),
    List(
      CellIndex(1, 0, 0),
      CellIndex(0, 1, 1),
      CellIndex(-1, 0, 2),
      CellIndex(-2, -1, 1),
      CellIndex(-1, -2, 0),
      CellIndex(0, -1, -1)),
    List(
      CellIndex(1, 1, 0),
      CellIndex(0, 1, 2),
      CellIndex(-2, 0, 2),
      CellIndex(-2, -2, 1),
      CellIndex(-1, -2, -1),
      CellIndex(1, -1, -1)))
}
