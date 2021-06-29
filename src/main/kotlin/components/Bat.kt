package components

import model.State
import materials.woodMaterial
import model.Constants.BAT_WIDTH
import model.Constants.BAT_Y
import model.Constants.BAT_Z
import react.RBuilder
import react.child
import utils.FC
import utils.interop.mesh
import utils.iterop.react.redux.useSelector
import utils.iterop.three.BoxGeometry
import utils.iterop.three.Vector3

// INTERESTING - If we use the model.BAT_WIDTH from TopLevelConstants instead, this code fails
//               due to a bug in the generated JavaScript - model.getBAT_WIDTH will be undefined
private val batGeometry = BoxGeometry(BAT_WIDTH, 1.0, 1.0).translate(BAT_WIDTH / 2, 0, 0)

val Bat = FC {
    val position = useSelector { state: State -> state.batPosition }

    mesh(Vector3(position, BAT_Y, BAT_Z), batGeometry) {
        woodMaterial()
    }
}
fun RBuilder.Bat() = child(Bat)


