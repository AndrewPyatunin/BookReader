package com.andreich.bookreader.ui.profilescreen

import com.andreich.bookreader.ui.ext.runCatchingCancellable
import com.andreich.bookreader_domain.usecase.EditProfileUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.tinkoff.kotea.core.CommandsFlowHandler

class ProfileSaveNewDataCommandHandler(
    private val editProfileUseCase: EditProfileUseCase
) : CommandsFlowHandler<ProfileCommand, ProfileEvent.ProfileCommandResultEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<ProfileCommand>): Flow<ProfileEvent.ProfileCommandResultEvent> {
        return commands.filterIsInstance<ProfileCommand.SaveNewData>()
            .mapLatest {
                runCatchingCancellable {
                    ProfileEvent.ProfileCommandResultEvent.DataSaved(editProfileUseCase.invoke(it.user))
                }.getOrElse {
                    ProfileEvent.ProfileCommandResultEvent.ShowError(it.message.orEmpty())
                }
            }
    }
}