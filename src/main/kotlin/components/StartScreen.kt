package components

import redux.StartGame
import model.State
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.child
import react.dom.button
import react.dom.div
import utils.FC
import utils.iterop.react.redux.useDispatch
import utils.iterop.react.redux.useSelector

fun RBuilder.StartScreen() = child(FC {
    val dispatch = useDispatch()
    val maps = useSelector { state: State -> state.maps }

    div("centered") {
        maps.map { (name, map) ->
            button(classes = "play-button") {
                attrs {
                    onClickFunction = { dispatch(StartGame(map)) }
                }
                +"Play map ${name}"
            }
        }
    }
})
