package triangle_tetris.graphics

import scalafx.scene.{Scene => FxScene}
import scalafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color._
import triangle_tetris.game.board.Grid
import triangle_tetris.game.EventHandler

class Screen(width: Double, height: Double, padding: Double, eventHandler: EventHandler) {
  private def noop: () => Unit = () => ()

  private val _width = width
  private val _height = height

  def render(grid: Grid): FxScene = {
    val screenElements = grid.cells.toList.map(ScreenElement(_))

    val canvas = new Canvas(width + padding, height + padding) {
      graphicsContext2D.lineWidth = 1
      graphicsContext2D.stroke = White

      screenElements.foreach(e => {
        graphicsContext2D.fill = e.color

        graphicsContext2D.fillPolygon(e.triangle.points
          .map(Pixel(_, _width, _height))
          .map(p => (p.x, p.y)))

        graphicsContext2D.strokePolygon(e.triangle.points
          .map(Pixel(_, _width, _height))
          .map(p => (p.x, p.y)))
      })

      translateX = padding
      translateY = padding
    }

    new FxScene {
      fill = Black
      onKeyPressed = keyEvent => {
        keyEvent.getCode match {
//          case KeyCode.LEFT => eventHandler.moveLeft()
//          case KeyCode.RIGHT => eventHandler.moveRight()
          case KeyCode.DOWN => eventHandler.moveDown()
//          case KeyCode.PAGE_DOWN => eventHandler.rotateRight()
//          case KeyCode.PAGE_UP => eventHandler.rotateLeft()
          case _ => noop
        }
      }
      content = new Pane { children = List(canvas) }
    }
  }
}
