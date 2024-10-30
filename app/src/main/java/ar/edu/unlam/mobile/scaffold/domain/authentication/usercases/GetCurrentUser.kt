package ar.edu.unlam.mobile.scaffold.domain.authentication.usercases

import com.google.firebase.auth.FirebaseUser

interface GetCurrentUser {
    suspend fun getCurrentUser(): FirebaseUser?
}