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
import ru.startandroid.develop.autentification.Routs
import ru.startandroid.develop.autentification.authentication.AuthState
import ru.startandroid.develop.autentification.authentication.AuthViewModel
import ru.startandroid.develop.autentification.login.LoginScreen
import ru.startandroid.develop.autentification.mainscreen.navigation_drawer_profile.NavigationDrawerProfile
import ru.startandroid.develop.autentification.petsscreen.profilepetscreen.ProfilePetScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate(Routs.LoginScreen.route)
            else -> Unit
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val navMainController = rememberNavController()
        NavHost(navController = navMainController, startDestination = Routs.HomeScreen.route) {
            composable(Routs.HomeScreen.route){
                NavigationDrawerProfile(navMainController, authViewModel)
            }
            composable(Routs.LoginScreen.route){
                LoginScreen(navController, authViewModel)
            }
            composable(Routs.ProfilePetScreen.route){
                ProfilePetScreen()
            }
        }
    }
}
