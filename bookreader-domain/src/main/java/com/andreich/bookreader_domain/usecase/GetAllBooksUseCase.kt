package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.model.Book
import com.andreich.bookreader_domain.repo.BookRepository
import kotlinx.coroutines.flow.Flow

class GetAllBooksUseCase(
    private val repository: BookRepository
) {

    suspend operator fun invoke(): Flow<List<Book>> {
        return repository.getBooks()
    }
}