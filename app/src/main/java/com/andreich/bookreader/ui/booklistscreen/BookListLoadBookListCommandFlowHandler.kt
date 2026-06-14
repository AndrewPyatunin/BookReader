package com.andreich.bookreader.ui.booklistscreen

import com.andreich.bookreader.ui.ext.runCatchingCancellable
import com.andreich.bookreader_domain.usecase.GetAllBooksUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.tinkoff.kotea.core.CommandsFlowHandler

class BookListLoadBookListCommandFlowHandler(
    private val getAllBooksUseCase: GetAllBooksUseCase
) : CommandsFlowHandler<BookListCommand, BookListEvent.BookListCommandResultEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<BookListCommand>): Flow<BookListEvent.BookListCommandResultEvent> {
        return commands.filterIsInstance<BookListCommand.LoadBookList>()
            .mapLatest {
                runCatchingCancellable {
                    BookListEvent.BookListCommandResultEvent.LoadingListSuccess(getAllBooksUseCase.invoke())
                }.getOrElse {
                    BookListEvent.BookListCommandResultEvent.Error(it.message.orEmpty())
                }
            }
    }
}