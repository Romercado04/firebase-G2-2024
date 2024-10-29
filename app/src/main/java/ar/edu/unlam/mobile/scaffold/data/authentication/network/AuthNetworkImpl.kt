package ar.edu.unlam.mobile.scaffold.data.authentication.network

import android.annotation.SuppressLint
import ar.edu.unlam.mobile.scaffold.domain.authentication.models.AuthRes
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthNetworkImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : AuthNetworkInterface  {
    //iniciamos sesion con la credencial de google
    @SuppressLint("SuspiciousIndentation")
    override suspend fun signInWithGoogle(credential: AuthCredential): AuthRes<FirebaseUser> =
        try{
        val firebaseUser = firebaseAuth.signInWithCredential(credential).await()
            firebaseUser.user?.let {
                AuthRes.Success(it)
            } ?: throw Exception("Sign in with Google failed")
        } catch (e: Exception){
            AuthRes.Error(e.message ?: "Sign in with Google failed")
            }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }

    override suspend fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthRes<FirebaseUser> =
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user
            if(firebaseUser != null){
                AuthRes.Success(firebaseUser)
            }else{
                AuthRes.Error("Authentication failed")
            }
        } catch (e: Exception){
            AuthRes.Error(e.message ?: "An error occurred while logging in")
        }

    override suspend fun createUserWithEmailAndPassword(
        name: String,
        email: String,
        password: String
    ): AuthRes<FirebaseUser> =
        try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user
            if(firebaseUser != null){
                val profileUpdate = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
                firebaseUser.updateProfile(profileUpdate).await()
                AuthRes.Success(firebaseUser)
            }else{
                AuthRes.Error("User creation failed: No user found")
            }
        }catch (e: Exception){
            AuthRes.Error(e.message ?: "An unknown error occurred")
        }

}

