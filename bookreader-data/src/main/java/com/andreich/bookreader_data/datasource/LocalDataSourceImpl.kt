package com.andreich.bookreader_data.datasource

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.andreich.bookreader_domain.model.User
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LocalDataSourceImpl : LocalDataSource {

    lateinit var context: Context

    lateinit var credentialManager: CredentialManager
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

        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId("")
            .setFilterByAuthorizedAccounts(true)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        credentialManager = CredentialManager.create(context)
        val result = credentialManager.getCredential(context, request)
        handleSignIn(result.credential)

    }

    override suspend fun logout() {
        auth.signOut()
        val clearRequest = ClearCredentialStateRequest()
        credentialManager.clearCredentialState(clearRequest)
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

    override suspend fun editUser(user: User): User {
        if (user.email != auth.currentUser?.email) {
            auth.currentUser?.verifyBeforeUpdateEmail(user.email)
            auth.currentUser?.sendEmailVerification()
        }
        return user
    }

    private fun handleSignIn(credential: Credential) {
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        } else {
        }
    }

private fun firebaseAuthWithGoogle(idToken: String) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
            } else {
            }
        }
}

}