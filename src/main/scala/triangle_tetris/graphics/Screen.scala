package triangle_tetris.graphics

import triangle_tetris.geometry.{Line, Point, Triangle}
import triangle_tetris.scene.Scene
import scalafx.scene.{Scene => FxScene}
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color._

case class Screen(width: Double, height: Double, padding: Double) {
  private def noop: () => Unit = () => ()

  private val _width = width
  private val _height = height

  def render(scene: Scene): FxScene = {
    val elements = scene.elements
      .flatMap(ScreenElement(_))

    val canvas = new Canvas(width + padding, height + padding) {
      graphicsContext2D.lineWidth = 1

      elements.foreach(e => {
        e.fillColor
          .map(ScreenColor(_))
          .foreach(graphicsContext2D.fill = _)
        e.strokeColor
          .map(ScreenColor(_))
          .foreach(graphicsContext2D.stroke = _)

        e.primitive match {
          case triangle: Triangle =>
            graphicsContext2D.fillPolygon(triangle.points
              .map(Pixel(_, _width, _height))
              .map(p => (p.x, p.y)))

            graphicsContext2D.strokePolygon(triangle.points
              .map(Pixel(_, _width, _height))
              .map(p => (p.x, p.y)))

          case line: Line =>
            line.points.map(Pixel(_, _width, _height)) match {
              case p1 :: p2 :: Nil =>
                graphicsContext2D.strokeLine(p1.x, p1.y, p2.x, p2.y)

              case _ => noop
            }
          case _ => noop
        }
      })

      translateX = padding
      translateY = padding
    }

    new FxScene {
      fill = Black
      content = new Pane { children = List(canvas) }
    }
  }
}
