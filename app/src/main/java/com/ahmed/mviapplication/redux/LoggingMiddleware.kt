package com.ahmed.mviapplication.redux

import android.util.Log

class LoggingMiddleware<S : State, A : Action> : Middleware<S, A> {


    override fun process(state: S, action: A, store: Store<S, A>) {
        Log.v(
            "LoggingMiddleware",
            "Processing action: $action; Current state: $state"
        )

    }
}