package ru.startandroid.develop.autentification.mainscreen.bottom_navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationBottomBar(
    navController: NavController
) {
    val listItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Pets,
        BottomNavigationItem.Profile
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        listItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                          selectedItemIndex = index
                          navController.navigate(item.route)
                          },
                label = {
                        Text(text = item.title)
                },
                icon = {
                        Icon(
                            imageVector = if(index == selectedItemIndex){
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title)
                    }
            )
        }
    }
}