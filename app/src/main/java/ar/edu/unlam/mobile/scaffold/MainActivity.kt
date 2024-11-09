package ar.edu.unlam.mobile.scaffold

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffold.ui.components.BottomBar
import ar.edu.unlam.mobile.scaffold.ui.navigation.NavigationComponent
import ar.edu.unlam.mobile.scaffold.ui.navigation.NavigationRoutes
import ar.edu.unlam.mobile.scaffold.ui.theme.MyApplicationTheme
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.messaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //se inicializan los servicios de firebase
        firebaseAnalytics = Firebase.analytics
        Firebase.messaging.isAutoInitEnabled = true
        //Se obtiene el token de firebase
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                println("FCM Token: $token")
                Log.d("MainActivity", "FCM Token: $token")
            } else {
                println("Error al obtener el token de FCM: ${task.exception}")
            }
        }
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    // Controller es el elemento que nos permite navegar entre pantallas. Tiene las acciones
    // para navegar como navigate y también la información de en dónde se "encuentra" el usuario
    // a través del back stack
    val controller = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    print("The currentRoute is: $currentRoute\n")
    Scaffold(
        bottomBar = {
            if (
                currentRoute == NavigationRoutes.Home.route ||
                currentRoute == NavigationRoutes.ListScreen.route ||
                currentRoute == NavigationRoutes.Profile.route
            ) {
                BottomBar(controller = controller)
            }
        },
    ) { paddingValue ->
        NavigationComponent(navController = controller, modifier = Modifier.padding(paddingValue))
    }
}