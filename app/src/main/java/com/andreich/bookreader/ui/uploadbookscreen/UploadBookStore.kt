package com.andreich.bookreader.ui.uploadbookscreen

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.kotea.core.Store

class UploadBookStore(
    private val uploadBookState: UploadBookState,
    private val uploadBookUpdate: UploadBookUpdate,
    private val commandFlowHandlers: List<CommandsFlowHandler<UploadBookCommand, UploadBookEvent>>
) : Store<UploadBookState, UploadBookEvent, UploadBookNews> by KoteaStore(
    initialState = uploadBookState,
    update = uploadBookUpdate,
    commandsFlowHandlers = commandFlowHandlers
)