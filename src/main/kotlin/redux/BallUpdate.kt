package redux

import model.BAT_WIDTH
import model.BrickDescriptor
import model.State
import model.hit
import model.isDestroyed
import model.Constants.BALL_RADIUS
import model.Constants.BOTTOM_HEIGHT
import model.Constants.GAME_HEIGHT
import model.Constants.GAME_WIDTH
import model.Constants.MAX_BALL_SPEED
import model.Constants.MAX_BEEP
import model.Position
import model.plus
import kotlin.math.sign
import kotlin.random.Random
import kotlin.random.nextInt

fun State.launchBall() = when {
    ballStuck -> copy(
        ballSpeedX = MAX_BALL_SPEED,
        ballSpeedY = MAX_BALL_SPEED,
        ballStuck = false
    )
    else -> this
}

fun State.stuckBallPosition() = Position(batPosition + BAT_WIDTH / 2, BOTTOM_HEIGHT)

fun State.calculateBallUpdate(timeDelta: Double): State {
    if (ballStuck) return this.copy(ballPosition = stuckBallPosition())

    // INTERESTING - Destructuring is not as neat in Kotlin compared to JS/TS
    var newScore = score
    var newSound = ""
    var newBallStuck = ballStuck
    var newBallSpeedX = ballSpeedX
    var newBallSpeedY = ballSpeedY
    var newBallPosition = ballPosition.plus(
        newBallSpeedX * timeDelta * speedScalar,
        newBallSpeedY * timeDelta * speedScalar
    )
    var newLives = lives
    var newBricks = bricks

    fun normalRebound() {
        val (rebound, hitBricks) = getRebound(newBallPosition)
        newBricks = newBricks.map { if (hitBricks.contains(it)) it.hit() else it }

        if (hitBricks.isNotEmpty()) {
            newScore += hitBricks.size * 10
            newSound = randomBeep()
        }

        if (rebound.contains(Rebound.Horizontal)) {
            newBallSpeedX = -newBallSpeedX
            newBallPosition = newBallPosition.copy(x = ballPosition.x)
        }
        if (rebound.contains(Rebound.Vertical)) {
            newBallSpeedY = -newBallSpeedY
            newBallPosition = newBallPosition.copy(y = ballPosition.y)
        }
    }

    fun bottomRebound() {
        if (newBallPosition.y >= BOTTOM_HEIGHT) return

        val missedBat = newBallPosition.x.outside(batPosition - BALL_RADIUS, batPosition + BAT_WIDTH + BALL_RADIUS)
        if (missedBat) {
            newLives--
            newBallStuck = true
            newSound = "explosion"
            return
        }

        newSound = "clang"

        when {
            isSheerBounce(newBallPosition.x) -> {
                newBallSpeedX = -ballSpeedX.sign * MAX_BALL_SPEED * 1.5
                newBallSpeedY = MAX_BALL_SPEED / 2
            }
            isReflectedBounce(newBallPosition.x) -> {
                newBallSpeedX = -ballSpeedX
                newBallSpeedY = -ballSpeedY
            }
            else -> {
                newBallSpeedX = ballSpeedX.sign * MAX_BALL_SPEED
                newBallSpeedY = MAX_BALL_SPEED
            }
        }

        newBallPosition = ballPosition
    }

    normalRebound()
    bottomRebound()

    return copy(
        lives = newLives,
        sound = newSound,
        ballPosition = newBallPosition,
        score = newScore,
        ballStuck = newBallStuck,
        ballSpeedX = newBallSpeedX,
        ballSpeedY = newBallSpeedY,
        bricks = newBricks
    )
}

// INTERESTING - Kotlin Standard library is better that JS/TS
fun randomBeep() = "beep" + Random.nextInt(1..MAX_BEEP)

fun State.isSheerBounce(newBallX: Double) =
    (newBallX <= batPosition + (BAT_WIDTH * 0.15) && ballSpeedX > 0) ||
    (newBallX >= batPosition + (BAT_WIDTH * 0.85) && ballSpeedX < 0)

fun State.isReflectedBounce(newBallX: Double) =
    (newBallX > batPosition + (BAT_WIDTH * 0.15) && newBallX <= batPosition + (BAT_WIDTH * 0.3) && ballSpeedX > 0) ||
    (newBallX >= batPosition + (BAT_WIDTH * 0.65) && newBallX < batPosition + (BAT_WIDTH * 0.85) && ballSpeedX < 0)

fun State.getRebound(newBallPosition: Position): Pair<Rebounds, List<BrickDescriptor>> {
    val hitBricks = bricks.filter {
        !it.isDestroyed() && brickBallCollisionTest(newBallPosition, it)
    }

    return when {
        hitBricks.isNotEmpty() -> getCollisionRebound(newBallPosition, hitBricks)
        newBallPosition.x.outside(0.0, GAME_WIDTH) -> Pair(listOf(Rebound.Horizontal), emptyList())
        newBallPosition.y > GAME_HEIGHT -> Pair(listOf(Rebound.Vertical), emptyList())
        else -> Pair(emptyList(), emptyList())
    }
}

private fun State.getCollisionRebound(
    newBallPosition: Position,
    hitBricks: List<BrickDescriptor>
): Pair<Rebounds, List<BrickDescriptor>> {
    val closestCollision = getClosestCollision(ballPosition, newBallPosition, hitBricks)
    return Pair(closestCollision.rebound, listOf(closestCollision.brick))
}

fun Double.outside(start: Double, end: Double) = this < start || this > end
