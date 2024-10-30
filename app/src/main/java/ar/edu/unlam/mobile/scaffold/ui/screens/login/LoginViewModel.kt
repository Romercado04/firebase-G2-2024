@file:Suppress("DEPRECATION")

package ar.edu.unlam.mobile.scaffold.ui.screens.login

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.R
import ar.edu.unlam.mobile.scaffold.domain.authentication.models.AuthRes
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.SignInWithEmailAndPassword
import ar.edu.unlam.mobile.scaffold.domain.authentication.usercases.SignInWithGoogle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogle,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPassword,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _loginUiState = mutableStateOf<LoginUiState>(LoginUiState.Success)
    val loginUiState: State<LoginUiState> = _loginUiState

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    //maneja el resultado de inicio de sesion
    fun handleSignInResult(task: Task<GoogleSignInAccount>): AuthRes<GoogleSignInAccount>? {
        return try {
            val account = task.getResult(ApiException::class.java)
            AuthRes.Success(account)
        } catch (e: ApiException) {
            AuthRes.Error(e.message ?: "Google sign-in ")
        }
    }

    suspend fun signInWithGoogleCredential(credential: AuthCredential): AuthRes<FirebaseUser> {
        return signInWithGoogleUseCase.signInWithGoogle(credential)
    }

    fun signInWithGoogle(googleSignInLauncher: ActivityResultLauncher<Intent>) {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    fun signInWithEmailAndPassword() {
        _loginUiState.value = LoginUiState.Loading
        viewModelScope.launch {
            try {
                signInWithEmailAndPasswordUseCase.signInWithEmailAndPassword(
                    email.value,
                    password.value
                )
                _loginUiState.value = LoginUiState.Success
            } catch (e: Exception) {
                _loginUiState.value = LoginUiState.Error
            }
        }
    }

    fun validateEmail(value: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$")
        return value.matches(emailRegex)
    }

    fun validatePassword(value: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[0-9]).{4,}$")
        return value.matches(passwordRegex)
    }


}