package com.andreich.bookreader.ui.uploadbookscreen

import androidx.compose.runtime.Composable

@Composable
fun DownloadScreen(k: Man) {
    when (k) {
        Man.Andrew -> k.age
        Man.Alex -> k.age
        Man.Amy -> k.age
    }
}