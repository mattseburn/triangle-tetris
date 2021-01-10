package triangle_tetris

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import triangle_tetris.geometry.{Line, Point, Triangle}
import triangle_tetris.graphics.Screen
import triangle_tetris.scene.{Node, Scene}

object TriangleTetris extends JFXApp {
  stage = new PrimaryStage {
    title = "Triangle Tetris"
    //scene = HexagonalGrid(12, 12, 50).render

    val _width = 600
    val _height = 600

    val xAxis: Node = Node(Some(Line(Point(-300, 0), Point(300, 0))), List())
    val yAxis: Node = Node(Some(Line(Point(0, 300), Point(0, -300))), List())
    val triangle: Node = Node(Some(Triangle(Point(100, 100), Point(200, 100), Point(200, 200))), List())
    val root: Node = Node(None, List(xAxis, yAxis, triangle))
    val _scene: Scene = Scene(root)
    val screen: Screen = Screen(_width, _height)

    println(_scene)

    scene = screen.render(_scene)
  }
}
