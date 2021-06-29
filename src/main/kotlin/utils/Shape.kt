package utils

import model.*
import utils.iterop.three.Vector2

fun circleCompassPoints(center: Position, radius: Double): List<Vector2> {
    return listOf(
        center.plusX(-radius).toVector2(),
        center.plusX(radius).toVector2(),
        center.plusY(-radius).toVector2(),
        center.plusY(radius).toVector2()
    )
}

fun rectangleCorners(bottomLeftCorner: Position, width: Double, height: Double): List<Vector2> {
    return listOf(
        bottomLeftCorner.toVector2(),
        bottomLeftCorner.plusX(width).toVector2(),
        bottomLeftCorner.plusY(height).toVector2(),
        bottomLeftCorner.plus(width, height).toVector2()
    )
}
