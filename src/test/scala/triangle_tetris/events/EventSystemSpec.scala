package triangle_tetris.events

import org.scalamock.scalatest.MockFactory
import org.scalatest.wordspec.AnyWordSpec
import triangle_tetris.events.Event._
import scala.language.postfixOps

class EventSystemSpec extends AnyWordSpec with MockFactory {
  "event system" when {
    "allow event handlers to be registered, and invoked" in {
      val es = new EventSystem()
      val mockHandler = mockFunction[Unit]

      es.registerHandler(MoveLeft, mockHandler)

      mockHandler.expects() returning() once

      es.handle(MoveLeft)
    }
  }
}
