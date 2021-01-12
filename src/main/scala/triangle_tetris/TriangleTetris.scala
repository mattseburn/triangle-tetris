package triangle_tetris

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import triangle_tetris.game.Grid
import triangle_tetris.game.pieces.PieceType
import triangle_tetris.geometry.{Line, Point, Triangle}
import triangle_tetris.graphics.Screen
import triangle_tetris.scene.{Node, Scene}

import scala.math._

/*

  - case object for PieceType (???)
  - case class for Piece (or abstract class?)
    - takes starting location
    - takes triangle magnitude
    - private method to create equilateral triangle at location with standard orientation
    - creates six triangles
    - uses transpose / rotate methods on triangles to arrange piece
    - has transpose / rotate methods for movement around the board

 */

object TriangleTetris extends JFXApp {
  stage = new PrimaryStage {
    title = "Triangle Tetris"
    //scene = HexagonalGrid(12, 12, 50).render

    val _width = 600
    val _height = 600

    val location = Point(100, 100)
    val magnitude = 25

    val xAxis: Node = Node(Some(Line(Point(-300, 0), Point(300, 0))), List())
    val yAxis: Node = Node(Some(Line(Point(0, 300), Point(0, -300))), List())
//    val pieces = List(
//      PieceType.Line()
//        .rotate(toRadians(120))
//        .transpose(Point(100, -120)),
//      PieceType.Hexagon()
//        .transpose(Point(-100, 100)),
//      PieceType.HourGlass()
//        .rotate(toRadians(60))
//        .transpose(Point(100, 50))
//    )

    val grid = Grid(12, 12, 10)

    val root: Node = Node(None, List(xAxis, yAxis, grid.node))
    val _scene: Scene = Scene(root)
    val screen: Screen = Screen(_width, _height)

    println(_scene)

    scene = screen.render(_scene)
  }
}
