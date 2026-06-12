package com.andreich.bookreader.ui.authscreen

import com.andreich.bookreader.ui.authscreen.AuthCommand.*
import ru.tinkoff.kotea.core.dsl.DslUpdate

class AuthUpdate : DslUpdate<AuthState, AuthEvent, AuthCommand, AuthNews>() {
    override fun NextBuilder.update(
        event: AuthEvent
    ) {
        when (event) {
            is AuthEvent.AuthCommandResultEvent.Error -> handleResult(event)
            is AuthEvent.AuthCommandResultEvent.Success -> handleResult(event)
            is AuthEvent.AuthUiEvent.SignIn -> handleUiEvent(event)
            is AuthEvent.AuthUiEvent.SignUp -> handleUiEvent(event)
            is AuthEvent.AuthUiEvent.SignInViaGoogle -> handleUiEvent(event)
        }
    }

    private fun NextBuilder.handleUiEvent(event: AuthEvent.AuthUiEvent) {
        when (event) {
            is AuthEvent.AuthUiEvent.SignIn -> {
                state { state.copy(isLoading = true) }
                commands(SignIn(event.email, event.password))
            }

            is AuthEvent.AuthUiEvent.SignUp -> {
                state { state.copy(user = event.user, isLoading = true) }
                commands(SignUp(user = event.user, password = event.password))
            }

            is AuthEvent.AuthUiEvent.SignInViaGoogle -> {
                state { state.copy(isLoading = true) }
                commands(SignInViaGoogle(email = event.email, password = event.password))
            }
        }
    }

    private fun NextBuilder.handleResult(event: AuthEvent.AuthCommandResultEvent) {
        when (event) {
            is AuthEvent.AuthCommandResultEvent.Error -> {
                news(AuthNews.ShowError(event.message))
                state { state.copy(isLoading = false) }
            }

            AuthEvent.AuthCommandResultEvent.Success -> {
                state { state.copy(isLoading = false) }
                news(AuthNews.NavigateTo())
            }
        }
    }
}