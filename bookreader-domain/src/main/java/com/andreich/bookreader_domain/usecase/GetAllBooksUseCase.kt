package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.model.Book
import com.andreich.bookreader_domain.repo.BookRepository

class GetAllBooksUseCase(
    private val repository: BookRepository
) {

    suspend operator fun invoke(): List<Book> {
        return repository.getBooks()
    }
}