package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.unlam.mobile.scaffold.R
import ar.edu.unlam.mobile.scaffold.ui.theme.CustomLightBlue

@Composable
fun BottomBar(controller: NavHostController) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    NavigationBar {
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == "habit" } == true,
            onClick = { controller.navigate("habit") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "",
                    tint = CustomLightBlue,
                )
            },
        )
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == "planner" } == true,
            onClick = { controller.navigate("planner") },
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "",
                    tint = CustomLightBlue,
                )
            },
        )
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == "timer" } == true,
            onClick = { controller.navigate("timer") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "",
                    tint = CustomLightBlue,
                )
            },
        )
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == "habit" } == true,
            onClick = { controller.navigate("home") },
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_equalizer),
                    contentDescription = "Borrar Tarea",
                    tint = CustomLightBlue,
                )
            },
        )
    }
}
