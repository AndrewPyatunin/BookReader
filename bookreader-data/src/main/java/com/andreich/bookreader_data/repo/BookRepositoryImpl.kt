package com.andreich.bookreader_data.repo

import com.andreich.bookreader_data.datasource.LocalDataSource
import com.andreich.bookreader_data.datasource.RemoteDataSource
import com.andreich.bookreader_domain.model.Book
import com.andreich.bookreader_domain.repo.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    val remoteDataSource: RemoteDataSource,
    val localDataSource: LocalDataSource
) : BookRepository {

    override suspend fun searchBook(param: String): Flow<List<Book>> {
        return remoteDataSource.getAllBooks().map { books ->
            books.filter {
                it.author.contains(param) || it.title.contains(param)
            }
        }
    }

    override suspend fun uploadBook(book: Book) {
        remoteDataSource.uploadBook(book)
    }

    override suspend fun downloadBook(book: Book) {
        TODO("Not yet implemented")
    }

    override suspend fun getBooks(): Flow<List<Book>> {
        return remoteDataSource.getAllBooks()
    }

    override suspend fun removeBook(book: Book) {
        TODO("Not yet implemented")
    }
}