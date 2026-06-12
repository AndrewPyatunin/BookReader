package com.andreich.bookreader.ui.booklistscreen

import com.andreich.bookreader.ui.ext.runCatchingCancellable
import com.andreich.bookreader_domain.usecase.SearchBookUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.tinkoff.kotea.core.CommandsFlowHandler

class BookListSearchBookCommandHandler(
    private val searchBookUseCase: SearchBookUseCase
) : CommandsFlowHandler<BookListCommand, BookListEvent.BookListCommandResultEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<BookListCommand>): Flow<BookListEvent.BookListCommandResultEvent> {
        return commands.filterIsInstance<BookListCommand.SearchBook>()
            .mapLatest {
                runCatchingCancellable {
                    BookListEvent.BookListCommandResultEvent.SearchResult(searchBookUseCase.invoke(it.param))
                }.getOrElse {
                    BookListEvent.BookListCommandResultEvent.Error(it.message.orEmpty())
                }
            }
    }
}