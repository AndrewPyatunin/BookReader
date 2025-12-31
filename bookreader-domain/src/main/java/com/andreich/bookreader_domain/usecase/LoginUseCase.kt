package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.repo.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(login: String, password: String) {
        return repository.login(login, password)
    }
}