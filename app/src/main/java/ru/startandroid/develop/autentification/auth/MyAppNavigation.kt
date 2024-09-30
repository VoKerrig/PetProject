package ru.startandroid.develop.autentification.auth

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyAppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_page") {
        composable("login_page"){
            LoginPage(navController, authViewModel)
        }
        composable("signup_page"){
            SignUpPage(navController, authViewModel)
        }
        composable("home_page"){
            HomePage(navController, authViewModel)
        }
    }
}