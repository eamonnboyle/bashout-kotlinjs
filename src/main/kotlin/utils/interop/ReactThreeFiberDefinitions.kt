package utils.interop

import kotlinext.js.jsObject
import org.w3c.dom.events.MouseEvent
import react.RBuilder
import react.RProps
import react.buildElements
import react.createElement
import utils.iterop.r3fiber.PointerEvent
import utils.iterop.three.FrontSide
import utils.iterop.three.Geometry
import utils.iterop.three.Side
import utils.iterop.three.Vector3

// INTERESTING - jsObject allows us to create empty objects of the correct type
fun RBuilder.ambientLight() = child(createElement<RProps>("ambientLight", jsObject {}))

interface MeshProps : RProps {
    var position: Vector3
    var geometry: Geometry
    var visible: Boolean
    var onPointerMove: ((PointerEvent) -> Unit)?
    var onClick: ((MouseEvent) -> Unit)?
}

fun RBuilder.mesh(
    position: Vector3,
    geometry: Geometry,
    visible: Boolean = true,
    onPointerMove: ((PointerEvent) -> Unit)? = null,
    onClick: ((MouseEvent) -> Unit)? = null,
    children: RBuilder.() -> Unit
) = child(createElement<MeshProps>("mesh", jsObject {
    this.position = position
    this.geometry = geometry
    this.visible = visible
    this.onPointerMove = onPointerMove
    this.onClick = onClick
}, buildElements(children)))

interface MeshStandardMaterialProps : RProps {
    var attach: String
    var color: String
    var flatShading: Boolean
    var emissiveIntensity: Int
    var side: Side
}

fun RBuilder.meshStandardMaterial(
    color: String = "white",
    emissiveIntensity: Int = 100
) = child(createElement<MeshStandardMaterialProps>("meshStandardMaterial", jsObject {
    this.color = color
    this.attach = "material"
    this.flatShading = false
    this.emissiveIntensity = emissiveIntensity
    this.side = FrontSide
}))

interface PointLightProps : RProps {
    var position: Vector3
}

fun RBuilder.pointLight(position: Vector3) = child(createElement<PointLightProps>("pointLight", jsObject {
    this.position = position
}))
