package com.andreich.bookreader_data.datasource

import com.andreich.bookreader_domain.model.Book
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllBooks(): Flow<List<Book>>

    suspend fun uploadBook(book: Book)
}