package ru.startandroid.develop.autentification

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ru.startandroid.develop.autentification.authentication.AuthViewModel
import ru.startandroid.develop.autentification.login.LoginScreen
import ru.startandroid.develop.autentification.login.SignUpScreen
import ru.startandroid.develop.autentification.login.data.LoginScreenObject
import ru.startandroid.develop.autentification.login.data.MainScreenDataObject
import ru.startandroid.develop.autentification.login.data.SignUpScreenObject
import ru.startandroid.develop.autentification.mainscreen.MainScreen
import ru.startandroid.develop.autentification.mainscreen.navigation_drawer_profile.NavigationDrawerProfile
import ru.startandroid.develop.autentification.petsscreen.PetsScreen

@Composable
fun AppNavigation(authViewModel: AuthViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LoginScreenObject ) {
        composable<LoginScreenObject> {
            LoginScreen(navController, authViewModel){
                navData -> navController.navigate(navData)
            }
        }
        composable<MainScreenDataObject>{ navEntry ->
            val navData = navEntry.toRoute<MainScreenDataObject>()
            MainScreen(navController, authViewModel)
        }
        composable<SignUpScreenObject>{
            SignUpScreen(navController, authViewModel){
                    navData -> navController.navigate(navData)
            }
        }
    }
}