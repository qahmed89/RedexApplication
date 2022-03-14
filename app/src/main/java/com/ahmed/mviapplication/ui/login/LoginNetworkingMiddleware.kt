package com.ahmed.mviapplication.ui.login

import com.ahmed.mviapplication.redux.Middleware
import com.ahmed.mviapplication.redux.Store
import com.ahmed.mviapplication.repo.LoginRepository

class LoginNetworkingMiddleware(
    private val repository: LoginRepository
) : Middleware<LoginViewState, LoginAction> {
    override fun process(
        state: LoginViewState,
        action: LoginAction,
        store: Store<LoginViewState, LoginAction>
    ) {
        when (action) {
            is LoginAction.SignInButtonClicked -> {
                if (!state.email.isNotEmpty()) {
                    store.dispatch(LoginAction.InvalidEmailSubmitted)
                    return
                }

                loginUser(state, store)
            }
        }
    }


    private fun loginUser(
        state: LoginViewState,
        store: Store<LoginViewState, LoginAction>
    ) {
        store.dispatch(LoginAction.LoginStarted)
        val isSuccessful = repository.login(state.email, state.password)
        if (isSuccessful) {
            store.dispatch(LoginAction.LoginCompleted)
        } else {
            store.dispatch(LoginAction.LoginFailed(null))
        }
    }
}