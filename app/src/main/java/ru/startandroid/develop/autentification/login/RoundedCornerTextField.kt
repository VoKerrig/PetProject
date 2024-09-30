package ru.startandroid.develop.autentification.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ru.startandroid.develop.autentification.R
import ru.startandroid.develop.autentification.ui.theme.CorgiColor

@Composable
fun EmailTextField(
    text: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = {
        onValueChange(it)
    },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, CorgiColor, RoundedCornerShape(20.dp)),
        label = {
            Text(text = label, color = Color.Gray)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}

@Composable
fun PasswordTextField(
    text: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, CorgiColor, RoundedCornerShape(20.dp)),
        label = {
            Text(text = label, color = Color.Gray)
        },
        singleLine = true,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisibility)
                R.drawable.visibility
            else R.drawable.visibility_off

            val description = if (passwordVisibility) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisibility = !passwordVisibility}){
                Icon(painter  = painterResource(id = image), description)
            }
        }
    )
}