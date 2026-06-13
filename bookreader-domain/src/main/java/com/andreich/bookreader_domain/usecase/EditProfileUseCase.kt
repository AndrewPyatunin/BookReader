package com.andreich.bookreader_domain.usecase

import com.andreich.bookreader_domain.model.User
import com.andreich.bookreader_domain.repo.AuthRepository

class EditProfileUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(user: User) : User {
        return repository.editUserInfo(user)
    }
}