package com.andreich.bookreader.ui.booklistscreen

import com.andreich.bookreader_domain.model.Book
import kotlinx.coroutines.flow.Flow

sealed interface BookListEvent {

    sealed interface BookListUiEvent : BookListEvent {

        class ChooseBook(val book: Book) : BookListUiEvent

        class RemoveBook(val book: Book) : BookListUiEvent

        class DownloadBook(val book: Book) : BookListUiEvent

        class SearchBook(val name: String) : BookListUiEvent

        object LoadBookList : BookListUiEvent
    }

    sealed interface BookListCommandResultEvent : BookListEvent {

        class LoadingListSuccess(val list: Flow<List<Book>>) : BookListCommandResultEvent

        class SearchResult(val list: Flow<List<Book>>) : BookListCommandResultEvent

        class Error(val message: String) : BookListCommandResultEvent

        class Success(val message: String) : BookListCommandResultEvent
    }
}