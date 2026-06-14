package com.andreich.bookreader.ui.bookreadscreen

import com.andreich.bookreader_domain.model.Book

sealed interface BookReadCommand {

    class EditFont(val font: Int) : BookReadCommand
}