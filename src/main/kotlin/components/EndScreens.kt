package components

import react.RBuilder
import react.child
import react.dom.div
import react.dom.img
import react.useEffectWithCleanup
import redux.ResetGame
import utils.FC
import utils.iterop.react.redux.useDispatch
import kotlinx.browser.window

val WinScreen = timeout(3000) {
    div("centered") {
        img("You won", "WinScreen.jpg") {}
    }
}

val EndScreen = timeout(3000) {
    div("centered") {
        img("You're dead", "DeathScreen.jpg") {}
    }
}

// INTERESTING - We can still do Functional composition but it is a little more clunky
//               due to the hoops we need to jump through to go from a simple function to
//               a React component in Kotlin. In TypeScript with JSX, this is much easier.
fun timeout(delay: Int, body: RBuilder.() -> Unit): RBuilder.() -> Unit {
    val component = FC {
        val dispatch = useDispatch()

        // INTERESTING - The second form of useEffect has explicitly a different name
        useEffectWithCleanup(emptyList()) {
            val handle = window.setTimeout({ dispatch(ResetGame()) }, delay)

            ({ window.clearTimeout(handle) })
        }

        body()
    }

    return { child(component) }
}

