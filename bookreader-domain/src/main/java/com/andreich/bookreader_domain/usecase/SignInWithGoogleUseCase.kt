package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.repo.AuthRepository

class SignInWithGoogleUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String) {
        return repository.loginWithGoogle(email, password)
    }
}