package triangle_tetris.scene

import triangle_tetris.geometry.Primitive

case class Node(primitive: Option[Primitive], children: List[Node]) {
  override def toString: String =
    s"Node[$primitive, ${children.mkString(", ")}"
}
