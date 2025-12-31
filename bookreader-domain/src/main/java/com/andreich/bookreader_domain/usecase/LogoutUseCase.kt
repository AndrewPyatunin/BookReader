package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.repo.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() {
        repository.logout()
    }
}