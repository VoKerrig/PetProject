package ru.startandroid.develop.autentification.petsscreen.detailscreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.startandroid.develop.autentification.R
import ru.startandroid.develop.autentification.petsscreen.detailscreen.data.DetailsNavObject

@Preview(showBackground = true)
@Composable
fun DetailsScreen(
    navObject: DetailsNavObject = DetailsNavObject()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box (modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
            AsyncImage(
                model = navObject.imageUrl,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .size(300.dp, 200.dp)
                .shadow(15.dp, shape = RoundedCornerShape(8.dp))
                .background(Color.White)
                .align(Alignment.Center)
                ){
                Text(text = stringResource(R.string.name))
            }
        }
    }
}