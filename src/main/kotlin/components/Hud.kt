package components

import redux.SetSoundActive
import redux.SetSpeedScalar
import model.State
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.child
import react.dom.div
import react.dom.h2
import react.dom.input
import react.dom.label
import utils.FC
import utils.iterop.react.redux.useDispatch
import utils.iterop.react.redux.useSelector

val Hud = FC {
    val dispatch = useDispatch()
    val lives = useSelector { state: State -> state.lives }
    val score = useSelector { state: State -> state.score }
    val speedScalar = useSelector { state: State -> state.speedScalar }
    val soundActive = useSelector { state: State -> state.soundActive }

    div {
        h2 { +"Lives: $lives" }
        h2 { +"Score: $score" }
        div {
            label { +"Speed:" }
            input(type = InputType.range) {
                attrs {
                    min = "1"
                    max = "50"

                    // INTERESTING - Bit ugly as doesn't support values
                    value = (speedScalar * 10).toString()
                    onChangeFunction = {
                        // INTERESTING - We have to cast target as the onChange is common
                        val input = it.target as HTMLInputElement
                        dispatch(SetSpeedScalar(input.valueAsNumber / 10))
                    }
                }
            }
        }
        div {
            label { +"Sound Active:" }
            input(type = InputType.checkBox) {
                attrs {
                    checked = soundActive
                    onChangeFunction = {
                        val input = it.target as HTMLInputElement
                        dispatch(SetSoundActive(input.checked))
                    }
                }
            }
        }
    }
}
fun RBuilder.Hud() = child(Hud)
