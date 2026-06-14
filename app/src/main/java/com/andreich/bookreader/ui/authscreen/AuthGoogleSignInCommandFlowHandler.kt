package com.andreich.bookreader.ui.authscreen

import com.andreich.bookreader.ui.ext.runCatchingCancellable
import com.andreich.bookreader_domain.usecase.SignInWithGoogleUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.tinkoff.kotea.core.CommandsFlowHandler

class AuthGoogleSignInCommandFlowHandler(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : CommandsFlowHandler<AuthCommand, AuthEvent.AuthCommandResultEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<AuthCommand>): Flow<AuthEvent.AuthCommandResultEvent> {
        return commands.filterIsInstance<AuthCommand.SignInViaGoogle>()
            .mapLatest {
                runCatchingCancellable {
                    signInWithGoogleUseCase.invoke(it.email, it.password)
                }.map {
                    AuthEvent.AuthCommandResultEvent.Success
                }.getOrElse {
                    AuthEvent.AuthCommandResultEvent.Error(it.message.orEmpty())
                }
            }
    }
}