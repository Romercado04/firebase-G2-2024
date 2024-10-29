package ar.edu.unlam.mobile.scaffold.data.authentication.repository

import ar.edu.unlam.mobile.scaffold.data.authentication.network.AuthNetworkInterface
import ar.edu.unlam.mobile.scaffold.domain.authentication.models.AuthRes
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authNetworkInterface: AuthNetworkInterface
) : AuthRepositoryInterface {
    override suspend fun signInWithGoogle(credential: AuthCredential): AuthRes<FirebaseUser> {
        return authNetworkInterface.signInWithGoogle(credential)
    }

    override suspend fun signOut() {
        authNetworkInterface.signOut()
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return authNetworkInterface.getCurrentUser()
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthRes<FirebaseUser> {
        return authNetworkInterface.signInWithEmailAndPassword(email, password)
    }

    override suspend fun createWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    ): AuthRes<FirebaseUser> {
        return authNetworkInterface.createUserWithEmailAndPassword(name, email, password)
    }

}