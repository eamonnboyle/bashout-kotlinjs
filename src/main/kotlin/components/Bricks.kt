package components

import model.State
import react.RBuilder
import react.child
import utils.FC
import utils.iterop.react.redux.useSelector

val Bricks = FC {
    val brickCount = useSelector { state: State -> state.bricks.size }

    repeat(brickCount) {
        Brick(it)
    }
}

fun RBuilder.Bricks() = child(Bricks)
