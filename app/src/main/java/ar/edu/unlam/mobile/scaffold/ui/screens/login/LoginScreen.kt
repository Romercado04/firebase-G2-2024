@file:Suppress("DEPRECATION")

package ar.edu.unlam.mobile.scaffold.ui.screens.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.domain.authentication.models.AuthRes
import ar.edu.unlam.mobile.scaffold.ui.components.LoginComponent
import ar.edu.unlam.mobile.scaffold.ui.navigation.NavigationRoutes
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navHostController: NavHostController) {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val googleSignInLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { activityResult ->
            when (val account = loginViewModel.handleSignInResult(
                GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
            )) {
                is AuthRes.Error -> {
                    Toast
                        .makeText(context, "Error: ${account.message}", Toast.LENGTH_SHORT)
                        .show()
                }

                is AuthRes.Success -> {
                    val credential = GoogleAuthProvider.getCredential(account.data.idToken, null)
                    scope.launch {
                        loginViewModel.signInWithGoogleCredential(credential)

                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                        navHostController.navigate(NavigationRoutes.Home.route) {
                            popUpTo(NavigationRoutes.Home.route) {
                                inclusive = true
                            }
                        }
                    }
                }

                null -> {
                    Toast.makeText(context, "Error Desconocido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    LoginComponent(navHostController, googleSignInLauncher)
}
