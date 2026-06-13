package com.andreich.bookreader.ui.uploadbookscreen

import com.andreich.bookreader.ui.ext.runCatchingCancellable
import com.andreich.bookreader_domain.usecase.UploadBookUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.tinkoff.kotea.core.CommandsFlowHandler

class UploadBookUploadCommandHandler(
    private val uploadBookUseCase: UploadBookUseCase
) : CommandsFlowHandler<UploadBookCommand, UploadBookEvent.UploadBookCommandResultEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<UploadBookCommand>): Flow<UploadBookEvent.UploadBookCommandResultEvent> {
        return commands.filterIsInstance<UploadBookCommand.UploadBook>()
            .mapLatest {
                runCatchingCancellable {
                    uploadBookUseCase.invoke(it.book)
                        .run { UploadBookEvent.UploadBookCommandResultEvent.UploadSuccess("Книга успешно выгружена") }
                }.getOrElse {
                    UploadBookEvent.UploadBookCommandResultEvent.Error(it.message.orEmpty())
                }
            }
    }
}