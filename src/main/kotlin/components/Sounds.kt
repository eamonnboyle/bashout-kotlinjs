package components

import model.State
import org.w3c.dom.Audio
import react.RBuilder
import react.child
import utils.FC
import utils.iterop.react.redux.useSelector

// INTERESTING - Infix functions can lead to very readable code
val sounds = mapOf(
    "beep1" to Audio("sounds/Beep1.mp3"),
    "beep2" to Audio("sounds/Beep2.mp3"),
    "beep3" to Audio("sounds/Beep3.mp3"),
    "beep4" to Audio("sounds/Beep4.mp3"),
    "beep5" to Audio("sounds/Beep5.mp3"),
    "beep6" to Audio("sounds/Beep6.mp3"),
    "beep7" to Audio("sounds/Beep7.mp3"),
    "beep8" to Audio("sounds/Beep8.mp3"),
    "died" to Audio("sounds/Died.mp3"),
    "win" to Audio("sounds/Win.mp3"),
    "explosion" to Audio("sounds/Explosion.mp3"),
    "clang" to Audio("sounds/Clang.mp3")
)

val Sounds = FC {
    val sound = useSelector { state: State -> state.sound }
    val soundActive = useSelector { state: State -> state.soundActive }

    when {
        !soundActive -> return@FC
        else -> sounds[sound]?.play()
    }
}
fun RBuilder.Sounds() = child(Sounds)
