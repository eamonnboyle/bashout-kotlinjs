package model

import utils.iterop.three.Vector2
import utils.iterop.three.Vector3

data class Position(val x: Double, val y: Double)

fun Position.plusX(xDelta: Double) = copy(x = x + xDelta)
fun Position.plusY(yDelta: Double) = copy(y = y + yDelta)
fun Position.plus(xDelta: Double, yDelta: Double) = Position(x + xDelta, y + yDelta)
fun Position.toVector2() = Vector2(x, y)
fun Position.toVector3(z: Double = 0.0) = Vector3(x, y, z)
