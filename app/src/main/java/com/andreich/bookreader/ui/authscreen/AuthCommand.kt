package com.andreich.bookreader.ui.authscreen

import com.andreich.bookreader_domain.model.User

sealed interface AuthCommand {

    data class SignUp(val user: User, val password: String = "") : AuthCommand

    data class SignIn(val email: String, val password: String) : AuthCommand

    class SignInViaGoogle(val email: String, val password: String) : AuthCommand
}