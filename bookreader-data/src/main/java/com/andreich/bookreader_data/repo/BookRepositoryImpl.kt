package com.andreich.bookreader_data.repo

import com.andreich.bookreader_data.datasource.LocalDataSource
import com.andreich.bookreader_data.datasource.RemoteDataSource
import com.andreich.bookreader_domain.model.Book
import com.andreich.bookreader_domain.repo.BookRepository

class BookRepositoryImpl(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) : BookRepository {

    override suspend fun searchBook(): Book {
        TODO("Not yet implemented")
    }

    override suspend fun uploadBook() {
        remoteDataSource.uploadBook()
    }

    override suspend fun getBooks(): List<Book> {
        return remoteDataSource.getAllBooks()
    }
}