package triangle_tetris

import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import triangle_tetris.game.Game
import triangle_tetris.graphics.Screen
import triangle_tetris.scene.Scene

object TriangleTetris extends JFXApp {
  stage = new PrimaryStage {
    title = "Triangle Tetris"

    val gridWidth = 12
    val gridHeight = 19
    val magnitude = 50
    val padding = 10
    val frameRate: Long = 5 * 1000 * 1000 * 1000 // nanoseconds

    val game: Game = new Game(gridWidth, gridHeight, magnitude, frameRate)
    val screen: Screen = Screen(game.width, game.height, padding, game.eventHandler)

    AnimationTimer { timestamp: Long =>
      scene = screen.render(Scene(game.cycle(timestamp)))
    }.start()
  }
}
