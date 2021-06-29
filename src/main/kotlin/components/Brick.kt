package components

import model.State
import model.isVisible
import kotlinext.js.jsObject
import model.toVector3
import react.RBuilder
import react.RProps
import react.child
import utils.FC
import utils.iterop.react.redux.useSelector

interface BrickProps : RProps {
    var index: Int
}

val Brick = FC<BrickProps> { props ->
    val brick = useSelector { state: State -> state.bricks[props.index] }

    if (brick.isVisible()) {
        Block(jsObject {
            position = brick.location.toVector3()
            color = brick.color
        })
    }
}

// INTERESTING - We can make our React components a bit easier to use
//              by accepting simple parameters rather than prop objects.
//              This is because in Kotlin it's not as easy to create typesafe object literals.
fun RBuilder.Brick(index: Int) = child(Brick, jsObject {
    this.index = index
})
