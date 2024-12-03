package ru.startandroid.develop.autentification.petsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.startandroid.develop.autentification.R
import ru.startandroid.develop.autentification.Routs
import ru.startandroid.develop.autentification.login.data.MainScreenDataObject
import ru.startandroid.develop.autentification.roomdb.MainViewModel


@Composable
fun PetsScreen(
    navController: NavController,
    navData: MainScreenDataObject,
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            contentAlignment = Alignment.BottomCenter
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
                .fillMaxHeight(0.7f)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                items(itemsList.value){ item ->
                    ListItem(
                        item, {
                        mainViewModel.nameEntity = it
                        mainViewModel.newText.value = it.name
                        },
                        {
                            mainViewModel.deleteItem(it)
                        }
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp),
            onClick = {
                navController.navigate(Routs.AddPetScreen.route)
            }
        ) {
            Text(text = "Добавить питомца")
        }
    }
}