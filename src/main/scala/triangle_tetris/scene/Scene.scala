package triangle_tetris.scene

import triangle_tetris.game.GameState

case class Scene(root: Node) {
  def elements: List[SceneElement] =
    _elements(root)

  private def _elements(node: Node): List[SceneElement] =
    node.sceneElement.toList ++ node.children.flatMap(_elements)

  override def toString: String =
    s"Scene[$root]"
}

object Scene {
  def apply(gameState: GameState): Scene =
    Scene(Node(None, List(Node(gameState.grid), Node(gameState.currentPiece))))
}