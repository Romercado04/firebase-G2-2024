package ar.edu.unlam.mobile.scaffold.domain.authentication.services

import ar.edu.unlam.mobile.scaffold.data.authentication.repository.AuthRepositoryInterface
import ar.edu.unlam.mobile.scaffold.domain.authentication.models.AuthRes
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.CreateNewAccount
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.GetCurrentUser
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.SignInWithEmailAndPassword
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.SignInWithGoogle
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.SignOut
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthService @Inject constructor(private val authRepository: AuthRepositoryInterface) :
    SignInWithGoogle, SignOut, GetCurrentUser,
    SignInWithEmailAndPassword, CreateNewAccount {
    override suspend fun signInWithGoogle(credential: AuthCredential): AuthRes<FirebaseUser> {
        return authRepository.signInWithGoogle(credential)
    }

    override suspend fun signOut() {
        authRepository.signOut()
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthRes<FirebaseUser> {
        return authRepository.signInWithEmailAndPassword(email, password)
    }

    override suspend fun createNewAccount(
        name: String,
        email: String,
        password: String
    ): AuthRes<FirebaseUser> {
        return authRepository.createWithEmailAndPassword(name, email, password)
    }
}