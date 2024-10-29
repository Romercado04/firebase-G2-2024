package ar.edu.unlam.mobile.scaffold.domain.authentication.usercases

import ar.edu.unlam.mobile.scaffold.domain.authentication.models.AuthRes
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface SignInWithGoogle {
    suspend fun signInWithGoogle(credential: AuthCredential): AuthRes<FirebaseUser>
}