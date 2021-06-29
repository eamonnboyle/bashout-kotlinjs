package components

import redux.GameTick
import react.RBuilder
import react.child
import react.dom.div
import react.redux.provider
import redux.store
import utils.FC
import utils.interop.ambientLight
import utils.interop.pointLight
import utils.iterop.r3fiber.Canvas
import utils.iterop.r3fiber.useFrame
import utils.iterop.react.redux.useDispatch
import utils.iterop.three.Vector3
import kotlinx.browser.window

val GameLoop = FC {
    val dispatch = useDispatch()

    // INTERESTING - useFrame can be used to execute code on every WebGL frame render
    //               It will provide the executed function with the time delta
    //
    //               NOTE: We need to use setImmediate as there is a bug which raises
    //               an error when the Canvas is removed
    useFrame { _, delta -> window.setTimeout({ dispatch(GameTick(delta))}, 0) }
}
fun RBuilder.GameLoop() = child(GameLoop)


// INTERESTING - Some components can be built directly.
//               When we need to do some work we use a FunctionalComponent.
fun RBuilder.PlayingScreen() =
    div("container") {
        div {
            Hud()
        }

        div("game-canvas") {
            Canvas {
                provider(store) {
                    ambientLight()
                    pointLight(position = Vector3(0.0, 0.0, 100.0))
                    GameLoop()
                    Controls()
                    Bricks()
                    Ball()
                    Bat()
                }
            }
        }
    }
