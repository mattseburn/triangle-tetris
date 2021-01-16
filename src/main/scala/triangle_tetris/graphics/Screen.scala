package triangle_tetris.graphics

import triangle_tetris.geometry.{Line, Point, Triangle}
import triangle_tetris.scene.Scene
import triangle_tetris.game.pieces.{Color => GameColor}
import scalafx.scene.{Scene => FxScene}
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color._

case class Screen(width: Double, height: Double, padding: Double) {
  private def noop = () => ()

  def render(scene: Scene): FxScene = {
    val elements = scene.elements
      .flatMap(ScreenElement(_))

    val canvas = new Canvas(width + padding, height + padding) {
      graphicsContext2D.lineWidth = 1

      elements.foreach(e => {
        e.fillColor.map {
          case GameColor.Red => Red
          case GameColor.Blue => Blue
          case GameColor.Green => Green
          case GameColor.White => White
        }.foreach(color => graphicsContext2D.fill = color)

        e.strokeColor.map {
          case GameColor.Red => Red
          case GameColor.Blue => Blue
          case GameColor.Green => Green
          case GameColor.White => White
        }.foreach(color => graphicsContext2D.stroke = color)

        e.primitive match {
          case triangle: Triangle =>
            graphicsContext2D.fillPolygon(triangle.points
              .map(pointToScreenCoordinates)
              .map(p => (p.x, p.y)))

            graphicsContext2D.strokePolygon(triangle.points
              .map(pointToScreenCoordinates)
              .map(p => (p.x, p.y)))
          case line: Line =>
            line.points.map(pointToScreenCoordinates) match {
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

  private def pointToScreenCoordinates(p: Point): Point =
    Point(p.x + width/2, -(p.y - height/2))
}
