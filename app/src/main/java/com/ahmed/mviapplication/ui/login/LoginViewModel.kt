package com.ahmed.mviapplication.ui.login

import androidx.lifecycle.ViewModel
import com.ahmed.mviapplication.redux.LoggingMiddleware
import com.ahmed.mviapplication.redux.Reducer
import com.ahmed.mviapplication.redux.Store
import com.ahmed.mviapplication.repo.MainLoginRepository
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
val store = Store(
    initialState = LoginViewState(),
    reducer = LoginReducer(),
    middlewares = listOf(
        LoggingMiddleware(),
        LoginNetworkingMiddleware(
            repository = MainLoginRepository(),
        ),
    )
)
    val loginState : StateFlow<LoginViewState> = store.state


    fun emailChanged(newEmail: String) {
        val action = LoginAction.EmailChanged(newEmail)
        store.dispatch(action)
    }

    fun passwordChanged(newPassword: String) {
        val action = LoginAction.PasswordChanged(newPassword)
        store.dispatch(action)
    }

    fun signInClicked() {
        val action = LoginAction.SignInButtonClicked
        store.dispatch(action)
    }

    fun invalidEmailSubmitted (){
        val action = LoginAction.InvalidEmailSubmitted
        store.dispatch(action)
    }

    fun loginStarted(){
        val action = LoginAction.LoginStarted
        store.dispatch(action)

    }

    fun loginCompleted (){
        val action = LoginAction.LoginCompleted
        store.dispatch(action)
    }
}