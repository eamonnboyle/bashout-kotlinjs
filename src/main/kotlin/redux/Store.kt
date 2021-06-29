package redux

import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import model.State

val client = HttpClient(Js) {
}

// INTERESTING - The rEnhancer adapts redux to Kotlin
//               We can use simpler action and redux.reducer objects for example
// INTERESTING - Extension functions like also are so useful when creating data
val store = createStore(::reducer, State(), rEnhancer()).also {
    loadMaps()
}

// INTERESTING - Using coroutines
suspend fun loadMap(url: String) {
    val map = client.get<String>(url) {
        port = window.location.port.toInt() // Not sure why I need to set the port
    }

//     val response = window.fetch(url).await()
//     val map = response.text().await()

    val bricks = parseMap(map.trim())

    store.dispatch(AddMap(Pair(url, bricks)))
}

fun loadMaps() {
    listOf(
        "maps/instil.txt",
        "maps/Senan.txt",
        "maps/Elena.txt",
        "maps/board1.txt",
        "maps/board2.txt",
        "maps/easy.txt"
    ).forEach {
        MainScope().launch {
            loadMap(it)
        }
    }
}
