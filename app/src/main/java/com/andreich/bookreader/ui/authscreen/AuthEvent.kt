package com.andreich.bookreader.ui.authscreen

import com.andreich.bookreader_domain.model.User

sealed interface AuthEvent {

    sealed interface AuthUiEvent : AuthEvent {

        data class SignUp(val user: User, val password: String = "") : AuthUiEvent

        data class SignIn(val email: String, val password: String) : AuthUiEvent

        data class SignInViaGoogle(val email: String, val password: String) : AuthUiEvent
    }

    sealed interface AuthCommandResultEvent : AuthEvent {

        object Success : AuthCommandResultEvent

        data class Error(val message: String) : AuthCommandResultEvent
    }
}