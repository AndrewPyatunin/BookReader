package com.andreich.bookreader.ui.uploadbookscreen

sealed interface UploadBookNews {

    class ShowError(val message: String) : UploadBookNews

    class ShowSuccessMessage(val message: String) : UploadBookNews
}