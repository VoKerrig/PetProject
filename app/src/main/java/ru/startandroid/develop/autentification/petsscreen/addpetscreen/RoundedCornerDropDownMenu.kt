package ru.startandroid.develop.autentification.petsscreen.addpetscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.startandroid.develop.autentification.ui.theme.CorgiColor

@Composable
fun RoundedCornerDropDownMenu(
    defPol: String,
    onItemSelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false ) }
    val selectedItem = remember { mutableStateOf( defPol) }
    val list = listOf(
        "Мужской",
        "Женский"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp)
            .border(1.dp, CorgiColor, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable {
                expanded.value = true
            }
            .padding(20.dp)
    ){
        Text(text = selectedItem.value)
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
            }) {
            list.forEach { item ->
                DropdownMenuItem(text = {
                    Text(text = item)
                },
                    onClick = {
                        selectedItem.value = item
                        expanded.value = false
                        onItemSelected(item)
                    })
            }
        }
    }
}