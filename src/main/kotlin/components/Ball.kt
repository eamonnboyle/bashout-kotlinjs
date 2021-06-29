package components

import materials.silverMaterial
import model.Constants
import model.Constants.BALL_Z
import model.State
import model.toVector3
import react.RBuilder
import react.child
import utils.FC
import utils.interop.mesh
import utils.iterop.react.redux.useSelector
import utils.iterop.three.SphereGeometry

private val ballGeometry = SphereGeometry(Constants.BALL_RADIUS, 16.0, 12.0)

val Ball = FC {
    val position = useSelector { state: State -> state.ballPosition }

    mesh(position.toVector3(BALL_Z), ballGeometry) {
        silverMaterial()
    }
}
fun RBuilder.Ball() = child(Ball)
