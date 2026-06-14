package com.andreich.bookreader.ui.profilescreen

import com.andreich.bookreader.ui.ext.runCatchingCancellable
import com.andreich.bookreader_domain.usecase.LogoutUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.tinkoff.kotea.core.CommandsFlowHandler

class ProfileSignOutCommandHandler(
    private val logoutUseCase: LogoutUseCase
) : CommandsFlowHandler<ProfileCommand, ProfileEvent.ProfileCommandResultEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<ProfileCommand>): Flow<ProfileEvent.ProfileCommandResultEvent> {
        return commands.filterIsInstance<ProfileCommand.SignOut>()
            .mapLatest {
                runCatchingCancellable {
                     logoutUseCase.invoke().run {
                        ProfileEvent.ProfileCommandResultEvent.SignOut
                    }
                }.getOrElse {
                    ProfileEvent.ProfileCommandResultEvent.ShowError(it.message.orEmpty())
                }
            }
    }
}