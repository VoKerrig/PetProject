package ru.startandroid.develop.autentification

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.startandroid.develop.autentification.authentication.AuthViewModel
import ru.startandroid.develop.autentification.login.LoginScreen
import ru.startandroid.develop.autentification.login.SignUpScreen
import ru.startandroid.develop.autentification.mainscreen.MainScreen

@Composable
fun AppNavigation(authViewModel: AuthViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_screen") {
        composable(Routs.LoginScreen.route){
            LoginScreen(navController,authViewModel)
        }
        composable(Routs.SignUpScreen.route){
            SignUpScreen(navController, authViewModel)
        }
        composable(Routs.MainScreen.route){
            MainScreen(navController, authViewModel)
        }
    }
}