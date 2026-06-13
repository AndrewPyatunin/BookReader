package com.andreich.bookreader.ui.profilescreen

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.kotea.core.Store

class ProfileStore(
    private val profileState: ProfileState,
    private val profileUpdate: ProfileUpdate,
    private val commandHandlers: List<CommandsFlowHandler<ProfileCommand, ProfileEvent.ProfileCommandResultEvent>>
) : Store<ProfileState, ProfileEvent, ProfileNews> by KoteaStore(
    initialState = profileState,
    update = profileUpdate,
    commandsFlowHandlers = commandHandlers
)