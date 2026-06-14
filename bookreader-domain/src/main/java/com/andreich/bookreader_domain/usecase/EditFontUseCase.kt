package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.repo.BookRepository

class EditFontUseCase(
    private val repository: BookRepository
) {

    suspend operator fun invoke(font: Int) {
        return repository.editFont(font)
    }
}