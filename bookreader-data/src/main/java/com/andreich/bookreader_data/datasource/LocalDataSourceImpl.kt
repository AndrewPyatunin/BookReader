package com.andreich.bookreader_data.datasource

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LocalDataSourceImpl : LocalDataSource {

    private val auth = Firebase.auth

    override suspend fun login(login: String, password: String) = suspendCoroutine { continuation ->
        auth.signInWithEmailAndPassword(login, password)
            .addOnSuccessListener { result ->
                continuation.resume(Unit)
            }.addOnFailureListener { exception ->
                continuation.resumeWithException(exception)
            }
    }

    override suspend fun loginWithGoogle(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override suspend fun signup(email: String, password: String) =
        suspendCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(Unit)
                    } else continuation.resumeWithException(task.exception?.cause ?: Throwable())
                }.addOnFailureListener {  }
        }

}