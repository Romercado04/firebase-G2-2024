package ar.edu.unlam.mobile.scaffold.ui.navigation

sealed class NavigationRoutes(val route: String) {
    object Login : NavigationRoutes("login")
    object CreateAccount : NavigationRoutes("createAccount")
    object Splash : NavigationRoutes("splashScreen")
    object Home : NavigationRoutes("home")
    object Profile : NavigationRoutes("profileScreen")
    object ListScreen : NavigationRoutes("listScreen")


}