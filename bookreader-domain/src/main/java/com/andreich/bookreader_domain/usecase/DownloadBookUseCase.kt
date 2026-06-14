package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.model.Book
import com.andreich.bookreader_domain.repo.BookRepository

class DownloadBookUseCase(
    private val repository: BookRepository
) {

    suspend operator fun invoke(book: Book) {
        return repository.downloadBook(book)
    }
}