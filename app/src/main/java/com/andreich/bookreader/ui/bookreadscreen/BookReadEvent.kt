package com.andreich.bookreader.ui.bookreadscreen

import com.andreich.bookreader_domain.model.Book

sealed interface BookReadEvent {

    sealed interface BookReadUiEvent : BookReadEvent {

        object NextPage : BookReadUiEvent

        object PreviousPage : BookReadUiEvent

        class ChoosePage(val page: Int) : BookReadUiEvent

        object OpenEditMode : BookReadUiEvent

        class EditFont(val font: Int) : BookReadUiEvent

        object ChangeTheme : BookReadUiEvent
    }

    sealed interface BookReadCommandResultEvent : BookReadEvent {

        class OpenBookSuccess(val book: Book, val page: Int) : BookReadCommandResultEvent

        class OpenBookFailed(val message: String) : BookReadCommandResultEvent

        class ChangeFontSuccess(val currentPage: Int, val finalPage: Int) : BookReadCommandResultEvent
    }

}