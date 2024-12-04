//package ru.startandroid.develop.autentification.mainscreen.bottom_navigation
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import ru.startandroid.develop.autentification.HomeScreen
//import ru.startandroid.develop.autentification.ProfileScreen
//import ru.startandroid.develop.autentification.petsscreen.PetsScreen
//
//@Composable
//fun BottomNavGraph(navController: NavHostController, modifier: Modifier) {
//    NavHost(navController = navController, startDestination = BottomNavigationItem.Home.route, modifier = Modifier.padding()) {
//        composable(BottomNavigationItem.Home.route) {
//            HomeScreen()
//        }
//        composable(BottomNavigationItem.Pets.route) {
//            PetsScreen(navController)
//        }
//        composable(BottomNavigationItem.Profile.route) {
//            ProfileScreen()
//        }
//    }
//}