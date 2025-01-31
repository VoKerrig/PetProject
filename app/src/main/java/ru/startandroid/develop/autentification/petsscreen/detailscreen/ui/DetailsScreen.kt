package ru.startandroid.develop.autentification.petsscreen.detailscreen.ui

import android.content.Intent
import android.provider.AlarmClock
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil3.compose.AsyncImage
import ru.startandroid.develop.autentification.R
import ru.startandroid.develop.autentification.petsscreen.detailscreen.data.DetailsNavObject
import java.util.ArrayList
import java.util.Calendar

@Preview(showBackground = true)
@Composable
fun DetailsScreen(
    navObject: DetailsNavObject = DetailsNavObject()
) {
    val petAge = when {
        navObject.age.toInt() >= 5 -> "${navObject.age} лет"
        navObject.age.toInt() in 2..4 -> "${navObject.age} года"
        else -> "${navObject.age} год"
    }

    val polPic = when (navObject.pol) {
        stringResource(R.string.pol_male) -> R.drawable.ic_male
        else -> R.drawable.ic_female
    }

//    val url = "tel:9232"
//    val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
    val context = LocalContext.current
    val week = ArrayList(
        listOf(
            Calendar.SUNDAY,
            Calendar.MONDAY,
            Calendar.TUESDAY,
            Calendar.WEDNESDAY,
            Calendar.THURSDAY,
            Calendar.FRIDAY,
            Calendar.SATURDAY
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box (modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)) {
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
                ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = navObject.name,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Image(
                            painterResource(polPic),
                            contentDescription = ""
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = navObject.breed)
                        Text(text = petAge)
                    }
                }
            }
            Button(onClick = {
                val clock = Intent(AlarmClock.ACTION_SET_ALARM)
                clock.putExtra(AlarmClock.EXTRA_MESSAGE, R.string.alarm_clock_pet)
                clock.putExtra(AlarmClock.EXTRA_DAYS, week)
                clock.putExtra(AlarmClock.EXTRA_HOUR, 14)
                clock.putExtra(AlarmClock.EXTRA_MINUTES, 29)
                clock.putExtra(AlarmClock.EXTRA_SKIP_UI, true)
                startActivity(context, clock, null)
            }) {
                Text(text = stringResource(R.string.alarm_clock_pet_button))
            }
        }
    }
}