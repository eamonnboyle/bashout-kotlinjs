@file:JsModule("react-redux")
@file:JsNonModule

package utils.iterop.react.redux

import redux.RAction

// INTERESTING - These functions are not present in the wrapper so added
//               explicitly here
@JsName("useSelector")
external fun <TState, TSelected> useSelector(selector: (TState) -> TSelected): TSelected

@JsName("useDispatch")
external fun useDispatch(): (RAction) -> RAction
