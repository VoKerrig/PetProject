package ru.startandroid.develop.autentification.mainscreen.navigation_drawer_profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import ru.startandroid.develop.autentification.Routs
import ru.startandroid.develop.autentification.authentication.AuthViewModel

sealed class NavigationDrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
) {
    object Main: NavigationDrawerItem("Главная", Icons.Filled.Home,Icons.Filled.Home, Routs.HomeScreen.route)
    object Info: NavigationDrawerItem("Инфо", Icons.Filled.Info, Icons.Filled.Info, Routs.LoginScreen.route)
    object Settings: NavigationDrawerItem("Профиль", Icons.Filled.Settings, Icons.Filled.Settings, Routs.LoginScreen.route)
}
