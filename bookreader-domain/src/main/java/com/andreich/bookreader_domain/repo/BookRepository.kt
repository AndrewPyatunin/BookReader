package com.andreich.bookreader_domain.repo

import com.andreich.bookreader_domain.model.Book

interface BookRepository {

    suspend fun searchBook(): Book

    suspend fun uploadBook()

    suspend fun getBooks(): List<Book>
}