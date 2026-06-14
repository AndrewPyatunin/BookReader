package com.andreich.bookreader.ui.bookreadscreen

sealed interface BookReadNews {

    object OpenEditMode : BookReadNews

    object ChangeTheme : BookReadNews

    class ShowError(val message: String) : BookReadNews
}