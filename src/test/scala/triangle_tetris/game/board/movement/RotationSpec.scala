package triangle_tetris.game.board.movement

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import triangle_tetris.game.board.movement.RotationalDirection._

class RotationSpec extends AnyWordSpec {
  "update" when {
    "passed clockwise" should {
      "decrease the step" in {
        val r = Rotation()

        r.update(Clockwise) shouldEqual Rotation(Step(-1))
      }
    }

    "passed counterclockwise" should {
      "increase the step" in {
        val r = Rotation()

        r.update(Counterclockwise) shouldEqual Rotation(Step(1))
      }
    }

    "called multiple times" should {
      "accumulate the steps, but cycle at +/- 6" in {
        val r = Rotation()

        r
          .update(Clockwise)
          .update(Clockwise)
          .update(Clockwise)
          .update(Clockwise)
          .update(Clockwise)
          .update(Clockwise) shouldEqual Rotation()

        r
          .update(Counterclockwise)
          .update(Counterclockwise)
          .update(Counterclockwise)
          .update(Counterclockwise)
          .update(Counterclockwise)
          .update(Counterclockwise) shouldEqual Rotation()
      }
    }
  }
}
