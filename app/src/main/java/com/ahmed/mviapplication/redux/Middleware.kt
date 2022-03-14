package com.ahmed.mviapplication.redux

interface Middleware<S : State, A : Action> {
    fun process(
        state: S,
        action: A,
        store: Store<S, A>
    )
}