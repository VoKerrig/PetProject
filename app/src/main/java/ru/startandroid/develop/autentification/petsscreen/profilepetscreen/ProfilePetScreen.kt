package ru.startandroid.develop.autentification.petsscreen.profilepetscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.startandroid.develop.autentification.ui.theme.CorgiColor

@Composable
fun ProfilePetScreen() {
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

        ProfilePetTextField(
            text = nameState,
            label = "Кличка"
        ) {
            nameState = it
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        ProfilePetTextField(
            text = breedState,
            label = "Порода"
        ) {
            breedState = it
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        ProfilePetTextField(
            text = textState,
            label = "Пол"
        ) {
            textState = it
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        ProfilePetTextFieldAge(
            text = ageState,
            label = "Возраст"
        ) {
            ageState = it
        }

        Spacer(modifier = Modifier.padding(top = 50.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            onClick = {}
        ) {
            Text(text = "Добавить")
        }
    }
}