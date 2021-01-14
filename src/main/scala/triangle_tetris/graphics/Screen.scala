package triangle_tetris.graphics

import triangle_tetris.geometry.{Point, Primitive}
import triangle_tetris.scene.Scene
import scalafx.scene.{Scene => FxScene}
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color._

case class Screen(width: Double, height: Double, padding: Double) {
  def render(scene: Scene): FxScene = {
    //println(s"Rendering scene: $scene")
    val coordinates = toScreenCoordinates(scene.coordinates)
    //println(s"coordinates: ${coordinates.mkString("\n")}")

    val canvas = new Canvas(width + padding, height + padding) {
      graphicsContext2D.lineWidth = 1
      graphicsContext2D.stroke = White
      graphicsContext2D.fill = Red

      coordinates.foreach {
        case points@(p1 :: p2 :: p3 :: Nil) =>
          graphicsContext2D.strokePolygon(points.map(p => (p.x, p.y)))

        case points@(p1 :: p2 :: Nil) =>
          graphicsContext2D.strokeLine(p1.x, p1.y, p2.x, p2.y)

        case _ => ???
      }

      translateX = padding
      translateY = padding
    }

    new FxScene {
      fill = Black
      content = new Pane { children = List(canvas) }
    }
  }

  private def pointToScreenCoordinates(p: Point): Point =
    Point(p.x + width/2, -(p.y - height/2))

  private def toScreenCoordinates(coordinates: List[List[Point]]): List[List[Point]] =
    coordinates.map(_.map(pointToScreenCoordinates))
}
