package com.andreich.bookreader_domain.repo

import kotlinx.coroutines.flow.Flow
import com.andreich.bookreader_domain.model.Book

interface BookRepository {

    suspend fun searchBook(param: String): Flow<List<Book>>

    suspend fun uploadBook(book: Book)

    suspend fun downloadBook(book: Book)

    suspend fun getBooks(): Flow<List<Book>>

    suspend fun removeBook(book: Book)

    suspend fun editFont(font: Int)
}