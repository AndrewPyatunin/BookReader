package com.andreich.bookreader.ui.bookreadscreen

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.kotea.core.Store

class BookReadStore(
    private val bookReadState: BookReadState,
    private val bookReadUpdate: BookReadUpdate,
    private val commandHandlers: List<CommandsFlowHandler<BookReadCommand, BookReadEvent.BookReadCommandResultEvent>>
) : Store<BookReadState, BookReadEvent, BookReadNews> by KoteaStore(
    initialState = bookReadState,
    update = bookReadUpdate,
    commandsFlowHandlers = commandHandlers
) {
}