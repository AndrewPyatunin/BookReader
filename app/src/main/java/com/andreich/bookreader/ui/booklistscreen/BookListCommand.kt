package com.andreich.bookreader.ui.booklistscreen

import com.andreich.bookreader_domain.model.Book

sealed interface BookListCommand {

    object LoadBookList : BookListCommand

    class RemoveBook(val book: Book) : BookListCommand

    class DownloadBook(val book: Book) : BookListCommand

    class SearchBook(val param: String) : BookListCommand

    class ChooseBook(val book: Book) : BookListCommand
}