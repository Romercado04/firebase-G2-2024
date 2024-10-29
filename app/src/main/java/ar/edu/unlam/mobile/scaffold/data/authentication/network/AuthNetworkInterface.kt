package ar.edu.unlam.mobile.scaffold.data.authentication.network

import ar.edu.unlam.mobile.scaffold.domain.authentication.models.AuthRes
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface AuthNetworkInterface {
    suspend fun signInWithGoogle(credential: AuthCredential): AuthRes<FirebaseUser>

    suspend fun signOut()

    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser>

    suspend fun createUserWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    ): AuthRes<FirebaseUser>
}