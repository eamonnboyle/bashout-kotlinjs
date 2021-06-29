package redux

import model.GameState
import model.State
import model.isDestroyed

fun State.updateGame(delta: Double) = when {
    gameState !== GameState.Playing -> this
    else -> this
        .calculateBat(delta)
        .calculateBallUpdate(delta)
        .gameEndTest()
}

fun State.gameEndTest(): State = when {
    isDead() -> this.copy(gameState = GameState.Dead, sound = "died")
    allBricksDestroyed() -> this.copy(gameState = GameState.Win, sound = "win")
    else -> this
}

private fun State.allBricksDestroyed() = bricks.all { it.isDestroyed() }

private fun State.isDead() = lives == 0
