package com.ahmed.mviapplication.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Store<S : State, A : Action>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<Middleware<S, A>> = emptyList()
) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    fun dispatch(action: A) {
        val newState = reducer.reduce(_state.value, action)
        middlewares.forEach {
            it.process(state.value, action, this)
        }
        _state.value = newState
    }
}