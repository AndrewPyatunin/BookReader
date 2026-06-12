package com.andreich.bookreader.ui.booklistscreen

import com.andreich.bookreader.ui.ext.runCatchingCancellable
import com.andreich.bookreader_domain.usecase.DownloadBookUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.tinkoff.kotea.core.CommandsFlowHandler

class BookListDownloadCommandHandler(
    private val downloadBookUseCase: DownloadBookUseCase
) : CommandsFlowHandler<BookListCommand, BookListEvent.BookListCommandResultEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<BookListCommand>): Flow<BookListEvent.BookListCommandResultEvent> {
        return commands.filterIsInstance<BookListCommand.DownloadBook>()
            .mapLatest {
                runCatchingCancellable {
                    downloadBookUseCase.invoke(it.book).run {
                        BookListEvent.BookListCommandResultEvent.Success("Книга ${it.book.title} успешно загружена")
                    }
                }.getOrElse {
                    BookListEvent.BookListCommandResultEvent.Error(it.message.orEmpty())
                }
            }
    }
}