package com.andreich.bookreader_database

import kotlinx.coroutines.flow.Flow
import java.io.File

interface BookFirebaseDao {

    suspend fun getBooks(title: String? = null, author: String? = null): Flow<List<Book>>

    suspend fun addBook(title: String, author: String)

    suspend fun uploadBook(file: File, uploader: FileUploader): Result<Unit>
}