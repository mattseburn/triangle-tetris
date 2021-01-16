package triangle_tetris.scene

case class Scene(root: Node) {
  def elements: List[SceneElement] =
    _elements(root)

  private def _elements(node: Node): List[SceneElement] =
    node.sceneElement.toList ++ node.children.flatMap(_elements)

  override def toString: String =
    s"Scene[$root]"
}
