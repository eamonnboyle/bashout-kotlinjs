package utils

import react.RBuilder
import react.RProps
import react.functionalComponent

fun FC(func: RBuilder.(props: RProps) -> Unit) = functionalComponent<RProps> {
    func(it)
}

fun <P : RProps> FC(func: RBuilder.(props: P) -> Unit) = functionalComponent<P> {
    func(it)
}
