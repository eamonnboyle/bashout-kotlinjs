package redux

import model.GameState
import model.State
import model.BrickDescriptor
import model.Position

class SetScore(val score: Int) : RAction
class SetSpeedScalar(val speedScalar: Double) : RAction
class SetSoundActive(val soundActive: Boolean) : RAction
class StartGame(val bricks: List<BrickDescriptor>) : RAction
class GameTick(val delta: Double) : RAction
class ResetGame : RAction
class LaunchBall : RAction
class MouseMove(val position: Position) : RAction
class AddMap(val map: Pair<String, List<BrickDescriptor>>) : RAction

fun reducer(state: State, action: RAction) = when (action) {
    is SetScore -> state.copy(score = action.score)
    is LaunchBall -> state.launchBall()
    is SetSpeedScalar -> state.copy(speedScalar = action.speedScalar)
    is SetSoundActive -> state.copy(soundActive = action.soundActive)
    is AddMap -> state.copy(maps = state.maps + listOf(action.map))
    is ResetGame -> State(maps = state.maps, soundActive = state.soundActive)
    is StartGame -> state.copy(gameState = GameState.Playing, bricks = action.bricks)
    is GameTick -> state.updateGame(action.delta)
    is MouseMove -> state.copy(mousePosition = action.position)
    else -> state
}
