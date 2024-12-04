package ru.startandroid.develop.autentification.petsscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.startandroid.develop.autentification.R
import ru.startandroid.develop.autentification.Routs

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PetsScreen(
    navController: NavController,
//    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
//    val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList())

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier.size(230.dp),
                painter = painterResource(id = R.drawable.pets_screen),
                contentDescription ="top_petsScreen",
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
        ) {

        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            onClick = {
                navController.navigate(Routs.AddPetScreen.route)
            }
        ) {
            Text(text = "Добавить питомца")
        }
    }
}