package triangle_tetris.graphics

import scalafx.scene.{Scene => FxScene}
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color._
import triangle_tetris.events.{Event, EventSystem}
import triangle_tetris.game.board.grid.Grid

class Screen(width: Double, height: Double, cellMagnitude: Double, padding: Double, eventSystem: EventSystem) {
  private val _width = width
  private val _height = height

  private def drawScreenElement(gc: GraphicsContext, screenElement: ScreenElement): Unit = {
    gc.fill = screenElement.color
    gc.fillPolygon(screenElement.triangle.points
      .map(Pixel(_, _width, _height))
      .map(p => (p.x, p.y)))
    gc.strokePolygon(screenElement.triangle.points
      .map(Pixel(_, _width, _height))
      .map(p => (p.x, p.y)))
  }

  def render(grid: Grid): FxScene = {
    val screenElements = grid.cells.toList.map(ScreenElement(_, cellMagnitude))

    val canvas = new Canvas(width + padding, height + padding) {
      graphicsContext2D.lineWidth = 1
      graphicsContext2D.stroke = White

      screenElements.foreach(drawScreenElement(graphicsContext2D, _))

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
