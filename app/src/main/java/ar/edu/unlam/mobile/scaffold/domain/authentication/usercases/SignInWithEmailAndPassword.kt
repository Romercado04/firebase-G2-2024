package ar.edu.unlam.mobile.scaffold.domain.authentication.usercases

import ar.edu.unlam.mobile.scaffold.domain.authentication.models.AuthRes
import com.google.firebase.auth.FirebaseUser

interface SignInWithEmailAndPassword {
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser>
}