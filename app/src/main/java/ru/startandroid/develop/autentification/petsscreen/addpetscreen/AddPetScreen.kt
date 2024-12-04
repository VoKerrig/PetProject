package ru.startandroid.develop.autentification.petsscreen.addpetscreen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil3.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import ru.startandroid.develop.autentification.roomdb.MainViewModel
import ru.startandroid.develop.autentification.ui.theme.CorgiColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddPetScreen(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    var selectedPol = "Не указано"
    val name = remember {
        mutableStateOf("")
    }
    val breed = remember {
        mutableStateOf("")
    }
    val age = remember {
        mutableStateOf("")
    }
    val selectedImageUri = remember {
        mutableStateOf<Uri?>(null)
    }
    val firestore = remember {
        Firebase.firestore
    }
    val storage = remember {
        Firebase.storage
    }

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
            uri ->
        selectedImageUri.value = uri
    }

    Image(painter = rememberAsyncImagePainter(
        model = selectedImageUri.value),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 100.dp),
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
            value = name.value,
            label = "Кличка"
        ) {
            name.value = it
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        AddPetTextField(
            value = breed.value,
            label = "Порода"
        ) {
            breed.value = it
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        AddPetTextFieldAge(
            value = age.value,
            label = "Возраст"
        ) {
            age.value = it
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        RoundedCornerDropDownMenu {
                selectedItem -> selectedPol = selectedItem
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            onClick = {
                imageLauncher.launch("image/*")
            }
        ) {
            Text(text = "Добавить фото")
        }

        Spacer(modifier = Modifier.padding(top = 50.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp),
            onClick = {
                savePetImage(
                    selectedImageUri.value!!,
                    storage,
                    firestore,
                    Pet(
                        name = name.value,
                        breed = breed.value,
                        age = age.value,
                        pol = selectedPol
                    ),
                    onSaved = {
                        navController.popBackStack()
                    },
                    onError = {}
                )
            }
        ) {
            Text(text = "Готово")
        }
    }
}

private fun savePetImage(
    uri: Uri,
    storage: FirebaseStorage,
    firestore: FirebaseFirestore,
    pet: Pet,
    onSaved: () -> Unit,
    onError: () -> Unit
) {
    val timeStamp = System.currentTimeMillis()
    val storageRef = storage.reference
        .child("pet_images")
        .child("image_$timeStamp.jpg")
    val uploadTask = storageRef.putFile(uri)
    uploadTask.addOnSuccessListener {
        storageRef.downloadUrl.addOnSuccessListener { url ->
            savePetToFirestore(
                firestore,
                url.toString(),
                pet,
                onSaved = {
                    onSaved()
                },
                onError = {
                    onError()
                }
            )
        }
    }
}

private fun savePetToFirestore(
    firestore: FirebaseFirestore,
    url: String,
    pet: Pet,
    onSaved: () -> Unit,
    onError: () -> Unit
) {
    val db = firestore.collection("pets")
    val key = db.document().id
    db.document(key)
        .set(
            pet.copy(key = key, imageUrl = url)
        )
        .addOnSuccessListener {
            onSaved()
        }
        .addOnFailureListener{
            onError()
        }
}