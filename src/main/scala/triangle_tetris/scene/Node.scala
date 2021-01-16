package triangle_tetris.scene

import triangle_tetris.game.GameElement
import triangle_tetris.geometry.Primitive

case class Node(sceneElement: Option[SceneElement], children: List[Node]) {
  override def toString: String =
    s"Node[$sceneElement, ${children.mkString(", ")}"
}

object Node {
  def apply[P <: Primitive](gameElement: GameElement[P]): Node =
    new Node(Some(SceneElement(gameElement)), List())
}
