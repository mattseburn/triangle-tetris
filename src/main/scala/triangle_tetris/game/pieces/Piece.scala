package triangle_tetris.game.pieces

import triangle_tetris.game.GameElement
import triangle_tetris.geometry.{Point, Transformations, Triangle}

import scala.util.Random

abstract class Piece(pieceType: PieceType,
                     color: Color,
                     location: Point = GameElement.defaultLocation,
                     triangles: List[Triangle] = List(),
                     magnitude: Double = GameElement.defaultMagnitude,
                ) extends GameElement[Triangle](triangles, Some(color), Some(Color.White))
                  with Transformations[Piece]

object Piece {
  def apply(): Piece = Random.nextInt(3) match {
    case 0 => PieceType.Hexagon()
    case 1 => PieceType.Line()
    case 2 => PieceType.HourGlass()
  }
}

/*

  GameElement
    - list of geometric primitives
    - color
    - implements transformations

  Screen
    - has width & height
    - renders scene
    - or maybe a GameRenderer renders a scene on a screen?

  val renderer = new Renderer(screen)
  renderer.render(scene)

  ^this can happen in game runner
  loop:
  - calculate new piece position
  - update scene
  - render(scene)

  - piece has primitive & color
  - each piece type has a set color associated with it
  - game element (currently) has list primitives, but no color
    - should maybe be collection of primitives with a single color?
      - already is a collection
    - a single piece (with multiple primitives) should be a single game element
  - scene has tree of scene elements
  - scene elements have primitive & color

  - or maybe coordinate system is a property of the scene?
  - but then a Point will only make sense within that context

  ** currently 1:1 ratio between pixels and cartesian points **

  -> hierarchical tree of game elements in cartesian space
  -> flat list of screen elements in pixel space

  case class GameElement(primitives: List[Primitive], color: Color)
  case class ScreenElement(primitives: List[Primitive], color: Color)

  -> should have symmetrical relationship between game and screen
  -> allows for conversions

  Game context
  - Create game elements
  - elements consist of one or more primitives
  - elements have a color
  - elements can be transformed

  Geometry context
  - primitives exist in isolation
  - no associated color
  - primitives can be transformed
  - primitives expressed in cartesian space

  Scene context
  - Hierarchy of game elements

  Screen context
  - take scene as input
  - convert points in scene to pixels
    - pixels cannot be created directly from points
    - need to know screen dimensions
  - get flat list of screen elements from scene
  - convert to pixels when rendering
  - need dimensions
    - currently getting from grid

 */