package com.andreich.bookreader.ui.booklistscreen

import com.andreich.bookreader.ui.ext.runCatchingCancellable
import com.andreich.bookreader_domain.usecase.RemoveBookUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.tinkoff.kotea.core.CommandsFlowHandler

class BookListRemoveBookCommandHandler(
    private val removeBookUseCase: RemoveBookUseCase
) : CommandsFlowHandler<BookListCommand, BookListEvent.BookListCommandResultEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<BookListCommand>): Flow<BookListEvent.BookListCommandResultEvent> {
        return commands.filterIsInstance<BookListCommand.RemoveBook>()
            .mapLatest {
                runCatchingCancellable {
                    removeBookUseCase.invoke(it.book).run {
                        BookListEvent.BookListCommandResultEvent.Success("Удаление книги ${it.book.title} успешно")
                    }
                }.getOrElse {
                    BookListEvent.BookListCommandResultEvent.Error(it.message.orEmpty())
                }
            }
    }
}