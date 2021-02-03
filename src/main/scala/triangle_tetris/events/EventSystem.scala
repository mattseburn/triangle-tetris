package triangle_tetris.events

import com.typesafe.scalalogging.Logger

class EventSystem {
  private val noop = () => ()
  private var _handlers: Map[Event, () => Unit] = Map()
  private val logger = Logger[EventSystem]

  def registerHandler(event: Event, handler: () => Unit): Unit =
    _handlers = _handlers ++ Map(event -> handler)

  def handle(event: Event): Unit = {
    logger.debug(event.toString)
    _handlers.getOrElse(event, noop)()
  }
}
