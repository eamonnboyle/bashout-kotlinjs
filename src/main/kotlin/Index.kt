import components.App
import kotlinext.js.js
import react.redux.provider
import redux.store
import utils.interop.THREE.OrbitControls
import utils.iterop.r3fiber.extend
import kotlinx.browser.document

fun main() {
    extend(js {
        this.OrbitControls = ::OrbitControls
    })

    react.dom.render(document.getElementById("root")) {
        provider(store) {
            App()
        }
    }
}

