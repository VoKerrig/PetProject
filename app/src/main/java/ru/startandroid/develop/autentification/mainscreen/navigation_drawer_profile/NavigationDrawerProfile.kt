package ru.startandroid.develop.autentification.mainscreen.navigation_drawer_profile

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.startandroid.develop.autentification.HomeScreen
import ru.startandroid.develop.autentification.petsscreen.PetsScreen
import ru.startandroid.develop.autentification.ProfileScreen
import ru.startandroid.develop.autentification.mainscreen.bottom_navigation.NavigationBottomBar
import ru.startandroid.develop.autentification.Routs
import ru.startandroid.develop.autentification.authentication.AuthViewModel
import ru.startandroid.develop.autentification.login.LoginScreen
import ru.startandroid.develop.autentification.mainscreen.MainScreen
import ru.startandroid.develop.autentification.petsscreen.profilepetscreen.ProfilePetScreen
import ru.startandroid.develop.autentification.ui.theme.BarColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerProfile(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val listItems = listOf(
        NavigationDrawerItem.Main,
        NavigationDrawerItem.Info,
        NavigationDrawerItem.Settings
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                listItems.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.title)
                        },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route)
                            coroutine.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = BarColor
                    ),
                    title = {
                        Text(text = "Дружок")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutine.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBottomBar(navController = navController)
            }
        ) {
            NavHost(navController = navController, startDestination = Routs.HomeScreen.route) {
                composable(Routs.HomeScreen.route) {
                    HomeScreen(navController, authViewModel)
                }
                composable(Routs.PetsScreen.route) {
                    PetsScreen(navController)
                }
                composable(Routs.ProfileScreen.route) {
                    ProfileScreen()
                }
                composable(Routs.ProfilePetScreen.route){
                    ProfilePetScreen(navController)
                }
                composable(Routs.LoginScreen.route){
                    LoginScreen(navController, authViewModel)
                }
                composable(Routs.MainScreen.route){
                    MainScreen(navController, authViewModel)
                }
            }
        }
    }
}




