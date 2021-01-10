package triangle_tetris.graphics

import triangle_tetris.geometry.{Point, Primitive}
import triangle_tetris.scene.Scene
import scalafx.scene.{Scene => FxScene}
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color._

import scala.math._

case class Screen(width: Double, height: Double) {
  def render(scene: Scene): FxScene = {
    println(s"Rendering scene: $scene")
    val coordinates = toScreenCoordinates(scene.coordinates)
    println(s"coordinates: ${coordinates.mkString("\n")}")

    val canvas = new Canvas(width, height) {
      graphicsContext2D.lineWidth = 1
      graphicsContext2D.stroke = White
      graphicsContext2D.fill = Red

      coordinates.foreach {
        case points@(p1 :: p2 :: p3 :: List()) =>
          graphicsContext2D.strokePolygon(points.map(p => (p.x, p.y)))

        case points@(p1 :: p2 :: List()) =>
          graphicsContext2D.strokeLine(p1.x, p1.y, p2.x, p2.y)

        case _ => ???
      }
    }

    new FxScene {
      fill = Black
      content = new Pane { children = List(canvas) }
    }
  }

  private def toScreenCoordinates(coordinates: List[List[Point]]): List[List[Point]] =
    coordinates.map(_.map(p => Point(p.x + rint(width/2), -(p.y - rint(height/2)))))
}
