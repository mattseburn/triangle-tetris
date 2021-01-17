package triangle_tetris.game

class EventHandler(val moveRight: () => Unit,
                   val moveLeft: () => Unit,
                   val moveDown: () => Unit,
                   val rotateRight: () => Unit,
                   val rotateLeft: () => Unit) {}

