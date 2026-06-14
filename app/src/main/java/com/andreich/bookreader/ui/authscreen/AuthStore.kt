package com.andreich.bookreader.ui.authscreen

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.kotea.core.Store

class AuthStore(
    private val authState: AuthState,
    private val update: AuthUpdate,
    private val commandsFlowHandlers: List<CommandsFlowHandler<AuthCommand, AuthEvent>>
) : Store<AuthState, AuthEvent, AuthNews> by KoteaStore(
    initialState = authState,
    commandsFlowHandlers = commandsFlowHandlers,
    update = update
)