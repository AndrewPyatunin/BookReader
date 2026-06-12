package com.andreich.bookreader.ui.booklistscreen

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.kotea.core.Store

class BookListStore(
    private val bookListState: BookListState,
    private val bookListUpdate: BookListUpdate,
    private val commandsFlowHandlers: List<CommandsFlowHandler<BookListCommand, BookListEvent>>
) : Store<BookListState, BookListEvent, BookListNews> by KoteaStore(
    initialState = bookListState,
    commandsFlowHandlers = commandsFlowHandlers,
    update = bookListUpdate
)