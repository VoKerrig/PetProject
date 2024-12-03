package ru.startandroid.develop.autentification.mainscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ru.startandroid.develop.autentification.ProfileScreen
import ru.startandroid.develop.autentification.Routs
import ru.startandroid.develop.autentification.authentication.AuthState
import ru.startandroid.develop.autentification.authentication.AuthViewModel
import ru.startandroid.develop.autentification.login.LoginScreen
import ru.startandroid.develop.autentification.login.data.LoginScreenObject
import ru.startandroid.develop.autentification.login.data.MainScreenDataObject
import ru.startandroid.develop.autentification.mainscreen.navigation_drawer_profile.NavigationDrawerProfile
import ru.startandroid.develop.autentification.petsscreen.PetsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    navData: MainScreenDataObject
) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate(LoginScreenObject)
            else -> Unit
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Routs.HomeScreen.route) {
            composable(Routs.HomeScreen.route){
                NavigationDrawerProfile(authViewModel, navData)
            }
            composable<LoginScreenObject> {
                LoginScreen(navController, authViewModel){
                        navData -> navController.navigate(navData)
                }
            }
            composable(Routs.ProfileScreen.route){
                ProfileScreen()
            }
        }
    }
}
