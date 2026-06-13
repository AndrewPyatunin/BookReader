package com.andreich.bookreader.ui.profilescreen

sealed interface ProfileNews {

    object OpenGallery : ProfileNews

    object EditMode : ProfileNews

    class ShowError(val message: String) : ProfileNews

    object SaveSuccess : ProfileNews

    object SignOut : ProfileNews
}