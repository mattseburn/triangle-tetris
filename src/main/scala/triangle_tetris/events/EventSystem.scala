package triangle_tetris.events

class EventSystem {
  private val noop = () => ()
  private var _handlers: Map[Event, () => Unit] = Map()

  def registerHandler(event: Event, handler: () => Unit): Unit =
    _handlers = _handlers ++ Map(event -> handler)

  def handle(event: Event): Unit =
    _handlers.getOrElse(event, noop)()
}
