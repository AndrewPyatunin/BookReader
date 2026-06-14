package com.andreich.bookreader_data.datasource

import com.andreich.bookreader_domain.model.User

interface LocalDataSource {

    suspend fun login(login: String, password: String)

    suspend fun loginWithGoogle(email: String, password: String)

    suspend fun logout()

    suspend fun signup(email: String, password: String)

    suspend fun editUser(user: User): User
}