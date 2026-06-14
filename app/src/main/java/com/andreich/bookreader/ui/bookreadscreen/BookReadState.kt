package com.andreich.bookreader.ui.bookreadscreen

import com.andreich.bookreader_domain.model.Book

data class BookReadState(
    val book: Book,
    val currentPage: Int,
    val finalPage: Int
)