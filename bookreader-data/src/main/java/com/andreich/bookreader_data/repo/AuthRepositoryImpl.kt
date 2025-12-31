package com.andreich.bookreader_data.repo

import com.andreich.bookreader_data.datasource.LocalDataSource
import com.andreich.bookreader_domain.repo.AuthRepository

class AuthRepositoryImpl(
    private val localDataSource: LocalDataSource
) : AuthRepository {

    override suspend fun login(login: String, password: String) {
        localDataSource.login(login, password)
    }

    override suspend fun loginWithGoogle(email: String, password: String) {
        localDataSource.loginWithGoogle(email, password)
    }

    override suspend fun logout() {
        localDataSource.logout()
    }

    override suspend fun signup(email: String, password: String) {
        localDataSource.signup(email, password)
    }

}