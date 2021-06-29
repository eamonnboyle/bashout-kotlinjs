package materials

import react.RBuilder
import utils.interop.meshStandardMaterial

fun RBuilder.silverMaterial() = meshStandardMaterial(
    color = "gray",
    emissiveIntensity = 50
)

fun RBuilder.woodMaterial() = meshStandardMaterial(
    color = "brown",
    emissiveIntensity = 20
)
