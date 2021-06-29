package model

data class BrickDescriptor(
    val location: Position,
    val color: String,
    val value: Int
)

fun BrickDescriptor.hit() = copy(value = (value - 1).coerceAtLeast(0))
fun BrickDescriptor.isVisible() = value > 0
fun BrickDescriptor.isDestroyed() = value == 0
