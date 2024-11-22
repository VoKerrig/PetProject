package ru.startandroid.develop.autentification.petsscreen.addpetscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.startandroid.develop.autentification.Routs
import ru.startandroid.develop.autentification.roomdb.MainViewModel
import ru.startandroid.develop.autentification.ui.theme.CorgiColor

@Composable
fun AddPetScreen(
    navController: NavController,
//    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    var nameState by remember {
        mutableStateOf("")
    }
    var breedState by remember {
        mutableStateOf("")
    }
    var textState by remember {
        mutableStateOf("")
    }
    var ageState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 150.dp),
            fontSize = 36.sp,
            color = CorgiColor,
            text = "Карточка питомца")

        Spacer(modifier = Modifier.padding(top = 50.dp))

//        AddPetTextField(
//            value = mainViewModel.newText.value,
//            label = "Кличка",
//            onValueChange = {
//                mainViewModel.newText.value = it
//            }
//        )

        AddPetTextField(
            value = nameState,
            label = "Кличка"
        ) {
            nameState = it
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        AddPetTextField(
            value = breedState,
            label = "Порода"
        ) {
            breedState = it
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        AddPetTextField(
            value = textState,
            label = "Пол"
        ) {
            textState = it
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        AddPetTextFieldAge(
            text = ageState,
            label = "Возраст"
        ) {
            ageState = it
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            onClick = {}
        ) {
            Text(text = "Добавить фото")
        }

        Spacer(modifier = Modifier.padding(top = 50.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text(text = "Готово")
        }

        //        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 50.dp, end = 50.dp),
//            onClick = {
//                mainViewModel.insertItem()
//                navController.navigate(Routs.PetsScreen.route){
//                    popUpTo(Routs.HomeScreen.route)
//                }
//            }
//        ) {
//            Text(text = "Готово")
//        }
    }
}