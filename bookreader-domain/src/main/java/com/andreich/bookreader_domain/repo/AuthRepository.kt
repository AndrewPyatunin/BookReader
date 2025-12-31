package com.andreich.bookreader_domain.repo

interface AuthRepository {

    suspend fun login(login: String, password: String)

    suspend fun loginWithGoogle(email: String, password: String)

    suspend fun logout()

    suspend fun signup()
}