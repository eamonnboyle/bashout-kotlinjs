package redux

import model.BrickDescriptor
import model.*
import model.Constants.BALL_RADIUS
import model.Constants.BRICK_HALF_HEIGHT
import model.Constants.BRICK_HALF_WIDTH
import model.Constants.BRICK_HEIGHT
import model.Constants.BRICK_WIDTH
import utils.circleCompassPoints
import utils.iterop.three.Box2
import utils.iterop.three.Vector2
import utils.rectangleCorners

data class Collision(
    val brick: BrickDescriptor,
    val distance: Double,
    val rebound: Rebounds
)

enum class Rebound {
    Vertical,
    Horizontal
}

typealias Rebounds = List<Rebound>

fun brickBallCollisionTest(
    newBallPosition: Position,
    brick: BrickDescriptor
): Boolean {
    val brickBox = Box2(
        brick.location.toVector2(),
        brick.location.plus(BRICK_WIDTH, BRICK_HEIGHT).toVector2()
    )

    val ballCenter = newBallPosition.toVector2()
    val withinBrick = { point: Vector2 -> brickBox.containsPoint(point) }
    val withinBall = { point: Vector2 -> point.distanceTo(ballCenter) < BALL_RADIUS }

    val ballPoints = circleCompassPoints(newBallPosition, BALL_RADIUS)
    val brickPoints = rectangleCorners(brick.location, BRICK_WIDTH, BRICK_HEIGHT)

    return ballPoints.any(withinBrick) || brickPoints.any(withinBall)
}

fun brickBallCollisionDetail(
    oldBallPosition: Position,
    newBallPosition: Position,
    brick: BrickDescriptor
): Collision {
    val ballCenter = newBallPosition.toVector2()
    val brickCenter = brick.location.plus(BRICK_HALF_WIDTH, BRICK_HALF_HEIGHT).toVector2()

    val rebound = mutableListOf<Rebound>()

    // Test for collision if we only moved horizontally or vertically to determine kind of rebound
    if (brickBallCollisionTest(oldBallPosition.copy(y = newBallPosition.y), brick)) {
        rebound.add(Rebound.Vertical)
    }
    if (brickBallCollisionTest(oldBallPosition.copy(x = newBallPosition.x), brick)) {
        rebound.add(Rebound.Horizontal)
    }

    return Collision(
        brick,
        ballCenter.distanceTo(brickCenter),
        rebound
    )
}

fun getClosestCollision(
    oldBallPosition: Position,
    newBallPosition: Position,
    bricks: List<BrickDescriptor>
): Collision {
    val first = bricks.first()
    val remaining = bricks.drop(1)

    val closestCollision = { previous: Collision, current: BrickDescriptor ->
        val collision = brickBallCollisionDetail(oldBallPosition, newBallPosition, current)

        // INTERESTING - I miss the ternary operator
        if (collision.distance < previous.distance) collision else previous
    }

    val firstCollision = brickBallCollisionDetail(oldBallPosition, newBallPosition, first)

    return remaining.fold(firstCollision, closestCollision)
}
