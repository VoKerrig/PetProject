package ru.startandroid.develop.autentification

sealed class Routs (val route: String){
    object MainScreen: Routs("main_screen")
    object LoginScreen: Routs("login_screen")
    object SignUpScreen: Routs("signup_screen")
    object HomeScreen: Routs("home_screen")
    object PetsScreen: Routs("pets_screen")
    object ProfileScreen: Routs("profile_screen")


}