package com.andreich.bookreader_domain.repo

import com.andreich.bookreader_domain.model.User

interface AuthRepository {

    suspend fun login(login: String, password: String)

    suspend fun loginWithGoogle(email: String, password: String)

    suspend fun logout()

    suspend fun signup(email: String, password: String)

    suspend fun editUserInfo(user: User): User
}