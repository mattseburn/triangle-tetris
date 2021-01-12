package triangle_tetris.game

import triangle_tetris.geometry.{Line, Triangle}

case class Grid(columns: Double, columnHeight: Double, cellMagnitude: Double)
  extends GameElement[Line] {

  def children: List[Line] =
    lines

  def lines: List[Line] = ???

//  def column = {
//    Triangle()
//  }
}
