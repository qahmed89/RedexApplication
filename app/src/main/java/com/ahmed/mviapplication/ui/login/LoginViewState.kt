package com.ahmed.mviapplication.ui.login

import com.ahmed.mviapplication.redux.State

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val showProgressBar: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val errorMessage : String? = null
) : State
