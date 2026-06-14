package com.andreich.bookreader.ui.authscreen

import com.andreich.bookreader_domain.model.User

data class AuthState(
    val user: User? = null,
    val isLoading: Boolean
)