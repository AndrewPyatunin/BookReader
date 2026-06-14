package com.andreich.bookreader.ui.booklistscreen

import com.andreich.bookreader.ui.booklistscreen.BookListNews.*
import com.andreich.bookreader.ui.booklistscreen.BookListEvent.*
import ru.tinkoff.kotea.core.dsl.DslUpdate

class BookListUpdate : DslUpdate<BookListState, BookListEvent, BookListCommand, BookListNews>() {

    override fun NextBuilder.update(
        event: BookListEvent
    ) {
        when (event) {

            is BookListCommandResultEvent.Success -> {
                handleResult(event)
            }
            is BookListCommandResultEvent.SearchResult -> {
                handleResult(event)
            }
            is BookListCommandResultEvent.Error -> {
                handleResult(event)
            }
            is BookListCommandResultEvent.LoadingListSuccess -> {
                handleResult(event)
            }
            is BookListUiEvent.ChooseBook -> {
                handleUiEvent(event)
            }
            is BookListUiEvent.LoadBookList -> {
                handleUiEvent(event)
            }
            is BookListUiEvent.DownloadBook -> {
                handleUiEvent(event)
            }
            is BookListUiEvent.RemoveBook -> {
                handleUiEvent(event)
            }
            is BookListUiEvent.SearchBook -> {
                handleUiEvent(event)
            }
        }
    }

    private fun NextBuilder.handleUiEvent(event: BookListUiEvent) {
        when (event) {
            is BookListUiEvent.ChooseBook -> {
                news(NavigateTo())
            }
            BookListUiEvent.LoadBookList -> {
                state { state.copy(isLoading = true) }
                commands(BookListCommand.LoadBookList)
            }

            is BookListUiEvent.DownloadBook -> {
                commands(BookListCommand.DownloadBook(event.book))
            }
            is BookListUiEvent.RemoveBook -> {
                commands(BookListCommand.RemoveBook(event.book))
            }
            is BookListUiEvent.SearchBook -> {
                commands(BookListCommand.SearchBook(event.name))
            }
        }
    }

    private fun NextBuilder.handleResult(event: BookListCommandResultEvent) {
        when (event) {
            is BookListCommandResultEvent.Error -> {
                state { state.copy(isLoading = false) }
                news(ShowError(event.message))
            }
            is BookListCommandResultEvent.LoadingListSuccess -> {
                state { state.copy(bookList = event.list) }
            }

            is BookListCommandResultEvent.Success -> {
                news(ShowSuccessMessage(event.message))
            }
            is BookListCommandResultEvent.SearchResult -> {
                state { state.copy(bookList = event.list) }
            }
        }
    }
}