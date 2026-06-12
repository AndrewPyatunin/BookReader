package com.andreich.bookreader.ui.authscreen

import com.andreich.bookreader.ui.ext.runCatchingCancellable
import com.andreich.bookreader_domain.usecase.LoginUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.tinkoff.kotea.core.CommandsFlowHandler

class AuthSignInCommandFlowHandler(
    private val loginUseCase: LoginUseCase
) : CommandsFlowHandler<AuthCommand, AuthEvent.AuthCommandResultEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<AuthCommand>): Flow<AuthEvent.AuthCommandResultEvent> {
        return commands.filterIsInstance<AuthCommand.SignIn>()
            .mapLatest { command ->
                runCatchingCancellable {
                    loginUseCase.invoke(command.email, command.password)
                }.map {
                    AuthEvent.AuthCommandResultEvent.Success
                }.getOrElse {
                    AuthEvent.AuthCommandResultEvent.Error(it.message.orEmpty())
                }

            }
    }
}