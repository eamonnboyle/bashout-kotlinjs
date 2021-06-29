@file:JsModule("three")
@file:JsNonModule

package utils.iterop.three

open external class Geometry {
  fun translate(x: Number, y: Number, z: Number): Geometry
}

// INTERESTING - Kotlin has base Number type
external class SphereGeometry(
  radius: Number,
  widthSegments: Number,
  heightSegments: Number
) : Geometry

external class BoxGeometry(
  width: Number,
  height: Number,
  depth: Number
) : Geometry

external enum class Side

external val FrontSide: Side

external class Vector2(x: Double, y: Double) {
  val x: Double
  val y: Double

  fun distanceTo(other: Vector2): Double
}

external class Vector3(x: Double, y: Double, z: Double) {
  val x: Double
  val y: Double
  val z: Double
}

external class Box2(start: Vector2, end: Vector2) {
  fun containsPoint(point: Vector2): Boolean
}

