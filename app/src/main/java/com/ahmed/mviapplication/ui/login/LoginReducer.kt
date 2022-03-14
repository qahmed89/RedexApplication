package com.ahmed.mviapplication.ui.login

import android.util.Log
import com.ahmed.mviapplication.redux.Reducer

/**
 * This reducer is responsible for handling any [LoginAction], and using that to create
 * a new [LoginViewState].
 */

class LoginReducer : Reducer<LoginViewState, LoginAction> {

    /**
     * Side note: Notice that all of the functions are named in a way that they signify they're
     * returning a new state, and not just processing information. This helps keep your when statements
     * clear that they're returning stuff, so that context isn't lost.
     */

    override fun reduce(state: LoginViewState, action: LoginAction): LoginViewState {
        Log.i("Reducer Process" , "Now processing $action")
       return when (action) {
            is LoginAction.EmailChanged -> {
                stateWithNewEmail(state, action)
            }
            is LoginAction.PasswordChanged -> {
                stateWithNewPassword(state, action)
            }
            is LoginAction.LoginStarted -> {
                stateAfterLoginStarted(state)
            }
            is LoginAction.LoginCompleted -> {
                stateAfterLoginCompleted(state)
            }
            is LoginAction.InvalidEmailSubmitted -> {
                stateWithInvalidEmailError(state)
            }
            is LoginAction.LoginFailed -> {
                state.copy(
                    showProgressBar = false,
                    errorMessage = action.error?.message
                )
            }
            else -> state
        }
    }

    private fun stateWithInvalidEmailError(state: LoginViewState) =
        state.copy(
            emailError = "Please enter an email address.",
        )


    private fun stateAfterLoginCompleted(state: LoginViewState) =
        state.copy(
            showProgressBar = false
        )


    private fun stateAfterLoginStarted(state: LoginViewState) = state.copy(showProgressBar = true)


    private fun stateWithNewPassword(
        currentState: LoginViewState,
        action: LoginAction.PasswordChanged) = currentState.copy(password = action.newPassword)


    private fun stateWithNewEmail(
        currentState: LoginViewState,
        action: LoginAction.EmailChanged
    ) = currentState.copy(email = action.newEmail , emailError = null)


}