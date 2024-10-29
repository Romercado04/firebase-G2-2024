package ar.edu.unlam.mobile.scaffold.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.unlam.mobile.scaffold.ui.screens.login.LoginScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.createAccount.CreateAccountScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.splash.SplashScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.HomeScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.ListScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.profile.ProfileScreen

@Composable
fun NavigationComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = (NavigationRoutes.Splash.route),
        modifier = modifier){
        composable(NavigationRoutes.Splash.route) {
            SplashScreen(navHostController = navController)
        }
        composable(NavigationRoutes.Login.route) {
            LoginScreen(navHostController = navController)
        }
        composable(NavigationRoutes.CreateAccount.route) {
            CreateAccountScreen(navHostController = navController)
        }
        composable(NavigationRoutes.Home.route) {
            HomeScreen()
        }
        composable(NavigationRoutes.Profile.route) {
            ProfileScreen(modifier, controller = navController)
        }
        composable(NavigationRoutes.ListScreen.route) {
            ListScreen()
        }
    }
}