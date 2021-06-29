package redux

import model.Constants
import model.State

fun State.calculateBat(timeDelta: Double): State {
    if (mousePosition == null) return this

    val delta = batPosition - mousePosition.x
    val maxDelta = batSpeed * timeDelta * speedScalar

    val clippedDelta = delta.coerceIn(-maxDelta, maxDelta)
    val newPosition = batPosition - clippedDelta

    return copy(batPosition = newPosition.clipToBoard())
}

// INTERESTING - These small extension methods really help with code readability
private fun Double.clipToBoard() = coerceIn(0.0, Constants.GAME_WIDTH - Constants.BAT_WIDTH)
