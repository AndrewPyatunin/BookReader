package com.andreich.bookreader.ui.bookreadscreen

import com.andreich.bookreader.ui.ext.runCatchingCancellable
import com.andreich.bookreader_domain.usecase.EditFontUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.tinkoff.kotea.core.CommandsFlowHandler

class BookReadEditFontCommandHandler(
    private val editFontUseCase: EditFontUseCase
) : CommandsFlowHandler<BookReadCommand, BookReadEvent.BookReadCommandResultEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<BookReadCommand>): Flow<BookReadEvent.BookReadCommandResultEvent> {
        return commands.filterIsInstance<BookReadCommand.EditFont>()
            .mapLatest {
                runCatchingCancellable {
                    editFontUseCase.invoke(it.font).run {
                        BookReadEvent.BookReadCommandResultEvent.ChangeFontSuccess(0, 0)
                    }
                }.getOrElse {
                    BookReadEvent.BookReadCommandResultEvent.OpenBookFailed(it.message.orEmpty())
                }
            }
    }
}