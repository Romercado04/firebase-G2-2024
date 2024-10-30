package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.unlam.mobile.scaffold.ui.navigation.NavigationRoutes

@Composable
fun BottomBar(controller: NavHostController) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    NavigationBar {
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == NavigationRoutes.Home.route } == true,
            onClick = { controller.navigate(NavigationRoutes.Home.route) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Borrar Tarea",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == NavigationRoutes.ListScreen.route } == true,
            onClick = { controller.navigate(NavigationRoutes.ListScreen.route) },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "Borrar Tarea",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == NavigationRoutes.Profile.route } == true,
            onClick = { controller.navigate(NavigationRoutes.Profile.route) },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Borrar Tarea",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
        )
    }
}
