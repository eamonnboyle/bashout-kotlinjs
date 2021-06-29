package components

import model.GameState
import model.State
import react.RBuilder
import react.child
import react.dom.div
import utils.FC
import utils.iterop.react.redux.useSelector

val App = FC {
    val gameState = useSelector { state: State -> state.gameState }

    div {
        div("title") {
            +"BASHout"
        }

        Sounds()

        // INTERESTING - A when expression with a sealed or enum type is really elegant
        when (gameState) {
            GameState.Start -> StartScreen()
            GameState.Playing -> PlayingScreen()
            GameState.Win -> WinScreen()
            GameState.Dead -> EndScreen()
        }
    }
}
fun RBuilder.App() = child(App)
