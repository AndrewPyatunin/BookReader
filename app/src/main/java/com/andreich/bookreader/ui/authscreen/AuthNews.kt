package com.andreich.bookreader.ui.authscreen

sealed interface AuthNews {

    class ShowError(val message: String) : AuthNews

    class NavigateTo() : AuthNews
}