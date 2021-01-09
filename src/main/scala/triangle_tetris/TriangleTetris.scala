package triangle_tetris

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

object TriangleTetris extends JFXApp {
  stage = new PrimaryStage {
    title = "Triangle Tetris"
    scene = new HexagonalGrid(12, 12, 50).render
  }
}
