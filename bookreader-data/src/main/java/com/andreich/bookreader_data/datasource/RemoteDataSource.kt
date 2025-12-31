package com.andreich.bookreader_data.datasource

import com.andreich.bookreader_domain.model.Book

interface RemoteDataSource {

    suspend fun getAllBooks(): List<Book>

    suspend fun getBook(): Book

    suspend fun uploadBook()
}