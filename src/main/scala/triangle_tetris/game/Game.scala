package triangle_tetris.game

class Game(magnitude: Double) {

  private def drawPieces = {}

  def run: Unit = ???

  /*

    - should the game render itself?
    - or return graphical elements to renderer?
    - or be given a renderer?
    - maybe pass the Scene back?
    - JFXApp probably doesn't have to be on the main object?
      - or maybe it does...

    - yes Game should only have game-related params, not pixels
    - maintains Scene with Node hierarchy
    - Scene can be passed for rendering
    - or maybe Screen is also passed as a param?

    - where should game loop be?
    - maybe have Game and GameRunner?

    val game = nem Game(gridWidth, gridHeight, cellMagnitude)

   */
}
