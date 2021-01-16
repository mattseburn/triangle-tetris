package triangle_tetris.game

import triangle_tetris.game.pieces.Color

import scala.math._
import triangle_tetris.geometry.{Line, Point}

case class Grid(gridWidth: Double, gridHeight: Double, cellMagnitude: Double)
  extends GameElement[Line](fillColor = None, strokeColor = Some(Color.White)) {

  import GameElement._

  def cellHeight: Double = triangleHeight(cellMagnitude)
  def width: Double = cellHeight * gridWidth
  def height: Double = cellMagnitude * gridHeight

  override val primitives: List[Line] = lines
  def lines: List[Line] = verticalLines ++ diagonalLines ++ borders

  case class Range(min: Double, max: Double)

  private def xRange =
    Range(rint(-width/2), rint(width/2))
  private def yRange =
    Range(rint(-height/2), rint(height/2))

  private def borders =
    List(topBorder, rightBorder, bottomBorder, leftBorder)

  private def topBorder =
    Line(
      Point(xRange.min, yRange.max),
      Point(xRange.max, yRange.max))

  private def leftBorder =
    Line(
      Point(xRange.min, yRange.min),
      Point(xRange.min, yRange.max))

  private def rightBorder =
    Line(
      Point(xRange.max, yRange.min),
      Point(xRange.max, yRange.max))

  private def bottomBorder =
    Line(
      Point(xRange.min, yRange.min),
      Point(xRange.max, yRange.min))

  private def verticalLines: List[Line] =
    (xRange.min.toInt to xRange.max.toInt by cellHeight.toInt).toList.map {
      case i if i % 2 == 0 =>
        Line(Point(i, yRange.max - cellMagnitude/2), Point(i, yRange.min + cellMagnitude/2))
      case i =>
        Line(Point(i, yRange.max), Point(i, yRange.min))
    }

  private def diagonalLines: List[Line] =
    (yRange.min.toInt + (cellMagnitude/2).toInt to yRange.max.toInt - (cellMagnitude/2).toInt by cellMagnitude.toInt).toList.map(i => {
      val pivot = Point(xRange.min, i)
      val l = Line(Point(xRange.min, yRange.max*2), Point(xRange.min, yRange.min*2))
        .rotate(pivot, toRadians(-60))

      val topIntersect = (l ^ topBorder)
        .getOrElse(throw new Exception("No intersection with top border"))
      val rightIntersect = (l ^ rightBorder)
        .getOrElse(throw new Exception("No intersection with right border"))
      val borderIntersect =
        if (Line(pivot, topIntersect).length < Line(pivot, rightIntersect).length) topIntersect
        else rightIntersect

      Line(pivot, borderIntersect)
    }) ++
    (yRange.min.toInt + (cellMagnitude/2).toInt to yRange.max.toInt - (cellMagnitude/2).toInt by cellMagnitude.toInt).toList.map(i => {
      val pivot = Point(xRange.min, i)
      val l = Line(Point(xRange.min, yRange.max*2), Point(xRange.min, yRange.min*2))
        .rotate(pivot, toRadians(60))

      val rightIntersect = (l ^ rightBorder)
        .getOrElse(throw new Exception("No intersection with right border"))
      val bottomIntersect = (l ^ bottomBorder)
        .getOrElse(throw new Exception("No intersection with top border"))
      val borderIntersect =
        if (Line(pivot, bottomIntersect).length < Line(pivot, rightIntersect).length) bottomIntersect
        else rightIntersect

      Line(pivot, borderIntersect)
    }) ++
    ((xRange.min + cellHeight).toInt to (xRange.max - cellHeight).toInt by (cellHeight*2).toInt).toList.map(i => {
      val pivot = Point(i, yRange.min)
      val l = Line(Point(xRange.min*2, yRange.min), Point(xRange.max*2, yRange.min))
        .rotate(pivot, toRadians(30))

      val rightIntersect = (l ^ rightBorder)
        .getOrElse(throw new Exception("No intersection with right border"))

      Line(pivot, rightIntersect)
    }) ++
    ((xRange.min + cellHeight).toInt to (xRange.max - cellHeight).toInt by (cellHeight*2).toInt).toList.map(i => {
      val pivot = Point(i, yRange.max)
      val l = Line(Point(xRange.min*2, yRange.max), Point(xRange.max*2, yRange.max))
        .rotate(pivot, toRadians(-30))

      val rightIntersect = (l ^ rightBorder)
        .getOrElse(throw new Exception("No intersection with right border"))

      Line(pivot, rightIntersect)
    })
}
