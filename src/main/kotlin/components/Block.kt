package components

import model.Constants.BRICK_HEIGHT
import model.Constants.BRICK_WIDTH
import react.RBuilder
import react.RProps
import react.child
import utils.FC
import utils.interop.mesh
import utils.interop.meshStandardMaterial
import utils.iterop.three.BoxGeometry
import utils.iterop.three.Vector3

val blockGeometry = BoxGeometry(BRICK_WIDTH - 0.1, BRICK_HEIGHT - 0.03, 2)
    .translate(BRICK_WIDTH / 2, BRICK_HEIGHT / 2, 0)

interface BlockProps : RProps {
    var position: Vector3
    var color: String
}

val Block = FC<BlockProps> { props ->
    mesh(position = props.position, geometry = blockGeometry) {
        meshStandardMaterial(props.color, emissiveIntensity = 100)
    }
}

fun RBuilder.Block(props: BlockProps) = child(Block, props)
