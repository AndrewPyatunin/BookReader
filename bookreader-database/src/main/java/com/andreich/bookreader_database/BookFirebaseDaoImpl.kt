package com.andreich.bookreader_database

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class BookFirebaseDaoImpl : BookFirebaseDao {

    private val db = Firebase.firestore

    override suspend fun getBooks(
        title: String?,
        author: String?
    ): Flow<List<Book>> {
        return db.collection("books")
            .snapshots().map {
                it.toObjects<Book>()
//                    .filter { book ->
//                        book.title == title || book.author == author
//                    }
            }
    }

    override suspend fun addBook(title: String, author: String) {
        return suspendCoroutine { continuation ->
            db.collection("books").add(Book(title, author))
                .addOnSuccessListener {
                    continuation.resume(Unit)
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }

    }

    override suspend fun uploadBook(file: File, uploder: FileUploader): Result<Unit> {
        val fileUploader: FileUploader = FileUploaderImpl()

        return fileUploader(file)
    }
}



