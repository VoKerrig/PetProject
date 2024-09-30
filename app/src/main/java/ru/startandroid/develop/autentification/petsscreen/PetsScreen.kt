package ru.startandroid.develop.autentification.petsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.startandroid.develop.autentification.R
import ru.startandroid.develop.autentification.roomdb.MainViewModel


@Composable
fun PetsScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            contentAlignment = Alignment.BottomCenter
        ){
            Image(
                painter = painterResource(id = R.drawable.pets_screen),
                contentDescription ="top_petsScreen",
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = mainViewModel.newText.value,
                    onValueChange = {
                        mainViewModel.newText.value = it
                },
                    label = {
                        Text(text = "Name...")
                    },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
                IconButton(
                    onClick = {
                        mainViewModel.insertItem()
                    }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
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
    }
}