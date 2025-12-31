package com.andreich.bookreader_database

import java.io.File

interface FileUploader {

    suspend operator fun invoke(file: File): Result<Unit>

}