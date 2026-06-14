package com.andreich.bookreader.ui.bookreadscreen

import com.andreich.bookreader.ui.bookreadscreen.BookReadNews.*
import ru.tinkoff.kotea.core.dsl.DslUpdate.*
import ru.tinkoff.kotea.core.dsl.DslUpdate

class BookReadUpdate : DslUpdate<BookReadState, BookReadEvent, BookReadCommand, BookReadNews>() {

    override fun NextBuilder.update(
        event: BookReadEvent
    ) {
        when (event) {
            is BookReadEvent.BookReadUiEvent.ChangeTheme -> {
                handleUiEvent(event)
            }

            is BookReadEvent.BookReadUiEvent.ChoosePage -> {
                handleUiEvent(event)
            }

            is BookReadEvent.BookReadUiEvent.EditFont -> {
                handleUiEvent(event)
            }

            is BookReadEvent.BookReadUiEvent.NextPage -> {
                handleUiEvent(event)
            }

            is BookReadEvent.BookReadUiEvent.OpenEditMode -> {
                handleUiEvent(event)
            }

            is BookReadEvent.BookReadUiEvent.PreviousPage -> {
                handleUiEvent(event)
            }

            is BookReadEvent.BookReadCommandResultEvent.OpenBookFailed -> {
                handleCommandResultEvent(event)
            }

            is BookReadEvent.BookReadCommandResultEvent.OpenBookSuccess -> {
                handleCommandResultEvent(event)
            }

            is BookReadEvent.BookReadCommandResultEvent.ChangeFontSuccess -> {
                handleCommandResultEvent(event)
            }
        }
    }

    private fun NextBuilder.handleUiEvent(event: BookReadEvent.BookReadUiEvent) {
        when (event) {
            is BookReadEvent.BookReadUiEvent.ChangeTheme -> {
                news(BookReadNews.ChangeTheme)
            }

            is BookReadEvent.BookReadUiEvent.ChoosePage -> {
                state { state.copy(currentPage = event.page) }
            }

            is BookReadEvent.BookReadUiEvent.EditFont -> {
                commands(BookReadCommand.EditFont(event.font))
            }

            is BookReadEvent.BookReadUiEvent.NextPage -> {
                state { state.copy(currentPage = state.currentPage+1) }
            }

            is BookReadEvent.BookReadUiEvent.OpenEditMode -> {
                news(BookReadNews.OpenEditMode)
            }

            is BookReadEvent.BookReadUiEvent.PreviousPage -> {
                state { state.copy(currentPage = state.currentPage-1) }
            }
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: BookReadEvent.BookReadCommandResultEvent) {
        when (event) {
            is BookReadEvent.BookReadCommandResultEvent.OpenBookFailed -> {
                news(ShowError(event.message))
            }

            is BookReadEvent.BookReadCommandResultEvent.OpenBookSuccess -> {
                state {
                    state.copy(
                        book = event.book,
                        currentPage = event.page,
                        finalPage = event.page
                    )
                }
            }

            is BookReadEvent.BookReadCommandResultEvent.ChangeFontSuccess -> {
                state { state.copy(currentPage = event.currentPage, finalPage = event.finalPage) }
            }
        }
    }
}