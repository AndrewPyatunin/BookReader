package com.andreich.bookreader.ui.uploadbookscreen

import com.andreich.bookreader_domain.model.Book

sealed interface UploadBookEvent {

    sealed interface UploadBookUiEvent : UploadBookEvent {

        object ChooseBookClick : UploadBookUiEvent

        class UploadBook(val book: Book) : UploadBookUiEvent
    }

    sealed interface UploadBookCommandResultEvent : UploadBookEvent {

        class UploadSuccess(val message: String) : UploadBookCommandResultEvent

        class Error(val message: String) : UploadBookCommandResultEvent

    }
}