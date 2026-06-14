package com.andreich.bookreader_data.datasource

import com.andreich.bookreader_domain.model.Book
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RemoteDataSourceImpl : RemoteDataSource {

    private val db = Firebase.firestore

    override fun getAllBooks(): Flow<List<Book>> {
        return db.collection("books").snapshots().map { snapshot ->
            snapshot.toObjects(Book::class.java)
        }
    }

    override suspend fun uploadBook(book: Book) = suspendCoroutine { continuation ->
        db.collection("books").add(book).addOnSuccessListener {
            continuation.resume(Unit)
        }.addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }
}