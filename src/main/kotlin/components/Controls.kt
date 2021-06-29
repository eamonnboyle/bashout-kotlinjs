package components

import model.Constants.CAMERA_FOCUS_POSITION
import model.Constants.CAMERA_POSITION
import model.Constants.GAME_CENTRE
import model.plus
import model.toVector3
import react.RBuilder
import react.child
import react.useEffect
import redux.LaunchBall
import redux.MouseMove
import utils.FC
import utils.interop.THREE.OrbitControls
import utils.interop.mesh
import utils.iterop.r3fiber.PointerEvent
import utils.iterop.r3fiber.useThree
import utils.iterop.react.redux.useDispatch
import utils.iterop.three.BoxGeometry

val mouseTrackGeomtry = BoxGeometry(400, 400, 1)

val Controls = FC {
    val dispatch = useDispatch()
    val three = useThree()

    fun move(e: PointerEvent) {
        dispatch(MouseMove(GAME_CENTRE.plus(e.uv.x * 400 - 200, e.uv.y * 400 - 200)))
    }

    useEffect {
        if (!three || !three.gl) return@useEffect

        val controls: dynamic = OrbitControls(three.camera, three.gl.domElement)
        controls.enableRotate = true
        controls.screenSpacePanning = true
        controls.minZoom = 1
        controls.maxZoom = 2500
        controls.zoomSpeed = 0.5
        controls.target.set(CAMERA_FOCUS_POSITION.x, CAMERA_FOCUS_POSITION.y, CAMERA_FOCUS_POSITION.z)
        three.camera.position.set(CAMERA_POSITION.x, CAMERA_POSITION.y, CAMERA_POSITION.z)
        three.camera.lookAt(CAMERA_FOCUS_POSITION.x, CAMERA_FOCUS_POSITION.y, CAMERA_FOCUS_POSITION.z)
        three.camera.updateProjectionMatrix()
    }

    mesh(
        position = GAME_CENTRE.toVector3(-1.0),
        visible = false,
        geometry = mouseTrackGeomtry,
        onPointerMove = ::move,
        onClick = { dispatch(LaunchBall()) }
    ) {}
}

fun RBuilder.Controls() = child(Controls)
