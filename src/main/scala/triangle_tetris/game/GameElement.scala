package triangle_tetris.game

import triangle_tetris.geometry.Primitive
import triangle_tetris.scene.Node

trait GameElement[P <: Primitive] {
  def children: List[P]
  val node: Node =
    Node(None, children.map(c => Node(Some(c), List())))
}
