package model

import model.Constants.GAME_CENTRE
import model.Constants.MAX_BALL_SPEED
import model.Constants.MAX_BAT_SPEED

enum class GameState {
    Start, Playing, Dead, Win
}

data class State (
    val ballPosition: Position = GAME_CENTRE.plusY(-5.0),
    val bricks: List<BrickDescriptor> = listOf(),
    val ballSpeedX: Double = MAX_BALL_SPEED,
    val ballSpeedY: Double = MAX_BALL_SPEED * 0.9,
    val batPosition: Double = GAME_CENTRE.x,
    val batSpeed: Double = MAX_BAT_SPEED,
    val speedScalar: Double = 1.0,
    val lives: Int = 3,
    val score: Int = 0,
    val ballStuck: Boolean = true,
    val sound: String = "",
    val soundActive: Boolean = true,
    val gameState: GameState = GameState.Start,
    val maps: List<Pair<String, List<BrickDescriptor>>> = emptyList(),
    val mousePosition: Position? = null
)
