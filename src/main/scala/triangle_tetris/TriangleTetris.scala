package triangle_tetris

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Label
import scalafx.scene.layout.{Border, BorderPane, BorderStroke, BorderStrokeStyle, BorderWidths, CornerRadii, Pane}
import scalafx.scene.paint.Color
import scalafx.scene.shape.ArcType

object TriangleTetris extends JFXApp {
  stage = new PrimaryStage {
    title = "Triangle Tetris"
    scene = new HexagonalGrid(12, 12, 50).render
  }
}
