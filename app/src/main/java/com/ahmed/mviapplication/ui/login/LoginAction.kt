package com.ahmed.mviapplication.ui.login

import com.ahmed.mviapplication.redux.Action

sealed class LoginAction : Action {
    data class EmailChanged(val newEmail: String) : LoginAction()
    data class PasswordChanged(val newPassword: String) : LoginAction()
    object SignInButtonClicked : LoginAction()
    object LoginStarted : LoginAction()
    object LoginCompleted : LoginAction()
    data class LoginFailed(val error: Throwable?) : LoginAction()
    object InvalidEmailSubmitted : LoginAction()
}