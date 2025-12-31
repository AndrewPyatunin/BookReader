package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.repo.AuthRepository

class SignupUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() {
        return repository.signup()
    }
}