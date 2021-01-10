package triangle_tetris.scene

import triangle_tetris.geometry.{Point, Primitive}

case class Scene(root: Node) {
  def coordinates: List[List[Point]] =
    _coordinates(root)

  private def _coordinates(node: Node): List[List[Point]] =
    node.primitive.map(p => p.points).toList ++ node.children.flatMap(_coordinates)

  override def toString: String =
    s"Scene[$root]"
}
