package triangle_tetris.graphics

import scalafx.scene.{Scene => FxScene}
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color._
import triangle_tetris.events.{Event, EventSystem}
import triangle_tetris.game.board.grid.Grid

class Screen(width: Double, height: Double, cellMagnitude: Double, padding: Double, eventSystem: EventSystem) {
  private val _width = width
  private val _height = height

  def render(grid: Grid): FxScene = {
    val screenElements = grid.cells.toList.map(ScreenElement(_, cellMagnitude))

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
      onKeyPressed = keyEvent => eventSystem.handle(Event(keyEvent.getCode))
      content = new Pane { children = List(canvas) }
    }
  }
}
