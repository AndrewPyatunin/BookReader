package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.model.Book
import com.andreich.bookreader_domain.repo.BookRepository

class SearchBookUseCase(
    private val repository: BookRepository
) {

    suspend operator fun invoke(): Book {
        return repository.searchBook()
    }
}