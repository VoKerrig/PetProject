package ru.startandroid.develop.autentification.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun LoginButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = {
        onClick()
    },
        modifier = Modifier.fillMaxWidth(0.5f)
    ) {
        Text(text = text)
    }
}