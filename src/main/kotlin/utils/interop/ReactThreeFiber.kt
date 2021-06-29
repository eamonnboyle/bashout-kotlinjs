@file:JsModule("@react-three/fiber")
@file:JsNonModule

package utils.iterop.r3fiber

import react.RClass
import react.RProps
import utils.iterop.three.Vector2

external val Canvas: RClass<RProps>

external fun extend(objects: Any)

external fun useFrame(callback: (dynamic, Double) -> Unit)

external fun useThree(): dynamic

external interface PointerEvent {
    val uv: Vector2
}
