package ar.edu.unlam.mobile.scaffold.data.authentication.repository

import ar.edu.unlam.mobile.scaffold.domain.authentication.models.AuthRes
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface AuthRepositoryInterface {
    suspend fun signInWithGoogle(credential: AuthCredential): AuthRes<FirebaseUser>

    suspend fun signOut()

    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser>

    suspend fun createWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    ): AuthRes<FirebaseUser>


}