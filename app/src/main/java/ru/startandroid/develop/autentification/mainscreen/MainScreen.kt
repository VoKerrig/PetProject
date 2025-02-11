package ru.startandroid.develop.autentification.mainscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.coroutines.launch
import ru.startandroid.develop.autentification.HomeScreen
import ru.startandroid.develop.autentification.ProfileScreen
import ru.startandroid.develop.autentification.R
import ru.startandroid.develop.autentification.Routs
import ru.startandroid.develop.autentification.authentication.AuthViewModel
import ru.startandroid.develop.autentification.login.data.MainScreenDataObject
import ru.startandroid.develop.autentification.mainscreen.bottom_navigation.BottomNavigationItem
import ru.startandroid.develop.autentification.mainscreen.bottom_navigation.NavigationBottomBar
import ru.startandroid.develop.autentification.mainscreen.navigation_drawer_profile.NavigationDrawerItem
import ru.startandroid.develop.autentification.petsscreen.PetsScreen
import ru.startandroid.develop.autentification.petsscreen.addpetscreen.AddPetScreen
import ru.startandroid.develop.autentification.petsscreen.detailscreen.data.DetailsNavObject
import ru.startandroid.develop.autentification.petsscreen.detailscreen.ui.DetailsScreen
import ru.startandroid.develop.autentification.ui.theme.BarColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    authViewModel: AuthViewModel,
    navData: MainScreenDataObject
) {
//    val authState = authViewModel.authState.observeAsState()
//
//    LaunchedEffect(authState.value) {
//        when(authState.value){
//            is AuthState.Unauthenticated -> navController.navigate(LoginScreenObject)
//            else -> Unit
//        }
//    }

    val listItems = listOf(
        NavigationDrawerItem.Main,
        NavigationDrawerItem.Info,
        NavigationDrawerItem.Settings
    )

    val navController = rememberNavController()
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
    )
    {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = BarColor
                    ),
                    title = {
                        Text(text = stringResource(R.string.top_bar_title))
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
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = BottomNavigationItem.Home.route, modifier = Modifier.padding(paddingValues)) {
                composable(BottomNavigationItem.Home.route) {
                    HomeScreen(authViewModel)
                }
                composable(BottomNavigationItem.Pets.route) {
                    PetsScreen(
                        navController,
                        onPetClick = {
                            navController.navigate(
                                DetailsNavObject(
                                    name = it.name,
                                    breed = it.breed,
                                    age = it.age,
                                    pol = it.pol,
                                    imageUrl = it.imageUrl
                                )
                            )
                        })
                }
                composable(BottomNavigationItem.Profile.route) {
                    ProfileScreen()
                }
                composable(Routs.AddPetScreen.route){
                    AddPetScreen(navController)
                }
                composable<DetailsNavObject>{
                        navEntry ->
                    val navData = navEntry.toRoute<DetailsNavObject>()
                    DetailsScreen(navData)
                }
            }
        }
    }
}
