package com.andreich.bookreader.ui.booklistscreen

import com.andreich.bookreader_domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class BookListState(
    val bookList: Flow<List<Book>> = emptyFlow(),
    val isLoading: Boolean = false
)