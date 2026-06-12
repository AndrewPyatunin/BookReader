package com.andreich.bookreader.ui.booklistscreen

import com.andreich.bookreader.ui.authscreen.AuthNews

interface BookListNews {

    class ShowError(val message: String) : BookListNews

    class ShowSuccessMessage(val message: String) : BookListNews
    class NavigateTo() : BookListNews
}