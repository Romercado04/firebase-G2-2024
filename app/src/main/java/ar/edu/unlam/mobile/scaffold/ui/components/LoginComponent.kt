package ar.edu.unlam.mobile.scaffold.ui.components

import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Text
import ar.edu.unlam.mobile.scaffold.R
import ar.edu.unlam.mobile.scaffold.ui.navigation.NavigationRoutes
import ar.edu.unlam.mobile.scaffold.ui.screens.login.LoginUiState
import ar.edu.unlam.mobile.scaffold.ui.screens.login.LoginViewModel

@Composable
fun LoginComponent(
    navHostController: NavHostController,
    googleSignInLauncher: ActivityResultLauncher<Intent>
) {
    val context = LocalContext.current
    val loginViewModel: LoginViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    when (loginViewModel.loginUiState.value) {
        is LoginUiState.Loading -> {
//                    LoadingComponent()
        }

        is LoginUiState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 25.dp, vertical = 20.dp),
            ) {
                // Título de la pantalla de inicio de sesión
                Column(
                    modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                ) {
                    Text(
                        text = "Inicio de Sesion",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    TextFieldOwn(
                        tittle = "Email",
                        placeholder = "abcd@gmail.com",
                    ) { email ->
                        loginViewModel.setEmail(email)
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    TextFieldPassword(
                        tittle = "Contraseña",
                        placeholder = "******",
                    ) { password ->
                        loginViewModel.setPassword(password)
                    }
                    Text(
                        text = "minimo 8 caracteres, una mayuscula y un numero",
                        fontSize = 12.sp,
                        color = Color.Red,
                    )
                }

                // Botón para iniciar sesión con Google
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(top = 25.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    IconButton(onClick = {
                        loginViewModel.signInWithGoogle(googleSignInLauncher = googleSignInLauncher)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_google),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(100.dp),
                        )
                    }
                }

                // Botón de inicio de sesión
                ExtendedFloatingActionButton(
                    onClick = {
                        if (loginViewModel.validateEmail(loginViewModel.email.value) &&
                            loginViewModel.validatePassword(loginViewModel.password.value)
                        ) {
                            loginViewModel.signInWithEmailAndPassword()
                            navHostController.navigate(NavigationRoutes.Home.route)
                        } else {
                            Toast
                                .makeText(
                                    context,
                                    "email o contraseña incorrecto",
                                    Toast.LENGTH_LONG,
                                ).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp, bottom = 5.dp),
                    containerColor = Color.DarkGray,
                ) {
                    Text(
                        text = "iniciar sesion",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                }

                // Redirección a la pantalla de registro
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "¿No tienes cuenta? ")
                    TextButton(onClick = {
                        navHostController.navigate(NavigationRoutes.CreateAccount.route)
                    }) {
                        Text(text = "Registrate")
                    }
                }
            }
        }

        is LoginUiState.Error -> {
            Toast.makeText(context, "Failed login", Toast.LENGTH_SHORT).show()
        }
    }
}