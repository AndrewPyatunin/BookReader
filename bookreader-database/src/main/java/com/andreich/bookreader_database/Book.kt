package com.andreich.bookreader_database

data class Book(
    val title: String,
    val author: String,
    val imageUri: String? = null,
    val loaded: Boolean = false
)