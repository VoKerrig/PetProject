package ru.startandroid.develop.autentification.petsscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ru.startandroid.develop.autentification.R
import ru.startandroid.develop.autentification.Routs
import ru.startandroid.develop.autentification.petsscreen.addpetscreen.Pet

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PetsScreen(
    navController: NavController,
    onPetClick: (Pet) -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val lazyListState: LazyListState = rememberLazyListState()
    val petsListState = remember {
        mutableStateOf(emptyList<Pet>())
    }

    LaunchedEffect(Unit) {
        val db = Firebase.firestore
        getAllPets(db) { pets ->
            petsListState.value = pets
        }
    }

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
                modifier = Modifier
                    .size(230.dp)
                    .padding(top = 10.dp),
                painter = painterResource(id = R.drawable.pets_screen),
                contentDescription ="top_petsScreen",
            )
        }

        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
        ) {
            items(petsListState.value){pet ->
                PetListItemUI(
                    pet,
                    onPetClick = {
                        onPetClick(it)
                    }
                )
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
            Text(text = stringResource(R.string.add_pet_button))
        }
    }
}

private fun getAllPets(
    db: FirebaseFirestore,
    onPets: (List<Pet>) -> Unit
) {
    db.collection("pets")
        .get()
        .addOnSuccessListener { task ->
            onPets(task.toObjects(Pet::class.java))
        }
}