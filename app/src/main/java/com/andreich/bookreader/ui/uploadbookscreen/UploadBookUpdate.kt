package com.andreich.bookreader.ui.uploadbookscreen

import ru.tinkoff.kotea.core.dsl.DslUpdate
import com.andreich.bookreader.ui.uploadbookscreen.UploadBookEvent.*

class UploadBookUpdate :
    DslUpdate<UploadBookState, UploadBookEvent, UploadBookCommand, UploadBookNews>() {

    override fun NextBuilder.update(
        event: UploadBookEvent
    ) {
        when (event) {
            is UploadBookCommandResultEvent.Error -> {
                handleResultEvent(event)
            }
            is UploadBookCommandResultEvent.UploadSuccess -> {
                handleResultEvent(event)
            }
            is UploadBookUiEvent.ChooseBookClick -> {
                handleUiEvent(event)
            }
            is UploadBookUiEvent.UploadBook -> {
                handleUiEvent(event)
            }
        }
    }

    private fun NextBuilder.handleUiEvent(event: UploadBookUiEvent) {
        when (event) {
            is UploadBookUiEvent.ChooseBookClick -> {
                commands(UploadBookCommand.ChooseBookClick)
            }
            is UploadBookUiEvent.UploadBook -> {
                state { state.copy(title = event.book.title, author = event.book.author) }
                commands(UploadBookCommand.UploadBook(event.book))
            }
        }
    }

    private fun NextBuilder.handleResultEvent(event: UploadBookCommandResultEvent) {
        when (event) {
            is UploadBookCommandResultEvent.Error -> {
                news(UploadBookNews.ShowError(event.message))
            }
            is UploadBookCommandResultEvent.UploadSuccess -> {
                news(UploadBookNews.ShowSuccessMessage(event.message))
            }
        }
    }
}