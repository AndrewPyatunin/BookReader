package com.andreich.bookreader_data.datasource

interface LocalDataSource {

    suspend fun login(login: String, password: String)

    suspend fun loginWithGoogle(email: String, password: String)

    suspend fun logout()

    suspend fun signup(email: String, password: String)
}