package triangle_tetris

import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import triangle_tetris.events.EventSystem
import triangle_tetris.game.Game
import triangle_tetris.screen.Screen

object TriangleTetris extends JFXApp {
  stage = new PrimaryStage {
    title = "Triangle Tetris"

    val gridWidth = 12
    val gridHeight = 18
    val magnitude = 50
    val padding = 10
    val frameRate: Long = 5 * 1000 * 1000 * 1000 // nanoseconds

    val eventSystem = new EventSystem()
    val game: Game = new Game(gridWidth, gridHeight, frameRate, eventSystem)
    val screen: Screen = new Screen(1000, 1000, magnitude, padding, eventSystem)

    AnimationTimer { timestamp: Long =>
      scene = screen.render(game.cycle(timestamp))
    }.start()
  }
}
