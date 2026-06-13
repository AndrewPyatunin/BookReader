package com.andreich.bookreader.ui.uploadbookscreen

import com.andreich.bookreader_domain.model.Book

sealed interface UploadBookCommand {

    object ChooseBookClick : UploadBookCommand

    class UploadBook(val book: Book) : UploadBookCommand
}