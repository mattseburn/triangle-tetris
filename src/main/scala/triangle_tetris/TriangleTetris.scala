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

    val gridWidth = 12
    val gridHeight = 19
    val magnitude = 50
    val padding = 10

    val grid = Grid(gridWidth, gridHeight, magnitude)

    val pieces = List(
      PieceType.Line()
        .rotate(toRadians(120))
        .transpose(Point(-2*grid.cellHeight, -150)),
      PieceType.Hexagon()
        .transpose(Point(2*grid.cellHeight, -50)),
      PieceType.HourGlass()
        .rotate(toRadians(60))
        .transpose(Point(4*grid.cellHeight, 100))
    )

    val root: Node = Node(None, List(Node(grid)) ++ pieces.map(Node(_)))
    val _scene: Scene = Scene(root)
    val screen: Screen = Screen(grid.width, grid.height, padding)

    scene = screen.render(_scene)
  }
}
