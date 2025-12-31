package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.repo.BookRepository

class UploadBookUseCase(
    private val repository: BookRepository
) {

    suspend operator fun invoke() {
        return repository.uploadBook()
    }
}