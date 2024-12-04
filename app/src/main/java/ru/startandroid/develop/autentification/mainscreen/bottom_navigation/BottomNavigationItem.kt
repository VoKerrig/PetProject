package ru.startandroid.develop.autentification.mainscreen.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import ru.startandroid.develop.autentification.Routs

sealed class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
    ) {
    object Home: BottomNavigationItem(
        "Главная",
        Icons.Filled.Home,
        Icons.Outlined.Home,
        Routs.MainScreen.route
    )
    object Pets: BottomNavigationItem(
        "Питомцы",
        Icons.Filled.Email,
        Icons.Outlined.Email,
        Routs.PetsScreen.route
    )
    object Profile: BottomNavigationItem(
        "Профиль",
        Icons.Filled.AccountCircle,
        Icons.Outlined.AccountCircle,
        Routs.ProfileScreen.route
    )
}
