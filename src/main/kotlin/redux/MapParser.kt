package redux

import model.BrickDescriptor
import model.Constants
import model.Position

fun mapColour(codedColour: Char) = when (codedColour) {
    'W' -> "white"
    'M' -> "magenta"
    'S' -> "silver"
    'R' -> "red"
    'G' -> "green"
    'B' -> "blue"
    'Y' -> "darkgoldenrod"
    'C' -> "cyan"
    else -> throw IllegalArgumentException("Unknown Brick Type - $codedColour")
}

val spaceBrick = BrickDescriptor(
    Position(0.0, 0.0),
    color = "white",
    value = 0
)

fun parseMap(map: String): List<BrickDescriptor> {
    return map.split("\n").mapIndexed { rowIndex, row ->
        row.split(" ").mapIndexed { colIndex, codedBrick ->
            if (codedBrick.trim() === "--") {
                spaceBrick
            } else {
                BrickDescriptor(
                    location = Position(colIndex.toDouble() * 3, Constants.GAME_HEIGHT - rowIndex.toDouble()),
                    color = mapColour(codedBrick[1]),
                    value = codedBrick[0].toString().toInt()
                )
            }
        }
    }.flatten()
}
