package ru.startandroid.develop.autentification

import android.content.Context
import android.location.Geocoder
import android.util.Log
import android.view.View.inflate
import android.widget.Toast
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.maps.android.compose.MapUiSettings
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen() {
    val home = LatLng(45.02415532515082, 39.03190712088517)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(home, 15f)
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val searchText = remember { mutableStateOf("") }
    val markers = remember { mutableStateListOf(home) }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        scope.launch {
                            cameraPositionState.move(
                                update = CameraUpdateFactory.scrollBy(
                                    -dragAmount.x,
                                    -dragAmount.y
                                )
                            )
                        }
                        change.consume()
                    }
                },
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                mapType = MapType.HYBRID,
                isTrafficEnabled = true
            ),
            uiSettings = MapUiSettings(
                compassEnabled = true,
                rotationGesturesEnabled = true,
            )
        ) {
            markers.forEach { marker ->
                Marker(
                    state = MarkerState(position = marker),
                    title = "Маркер"
                )
            }
        }

        Column(Modifier.padding(16.dp)) {
            val keyboardController = LocalSoftwareKeyboardController.current
            SearchView(searchText = searchText, keyboardController = keyboardController) { query ->
                scope.launch {
                    val location = getLocationFromAddress(context, query)
                    if (location != null) {
                        cameraPositionState.move(
                            update = CameraUpdateFactory.newLatLngZoom(location, 15f)
                        )
                        markers.clear()
                        markers.add(location)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchView(
    searchText: MutableState<String>,
    keyboardController: SoftwareKeyboardController?,
    onSearch: (String) -> Unit
) {
    TextField(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        },
        placeholder = { Text("Search location") },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(searchText.value)
                keyboardController?.hide()
            }
        )
    )
}

fun getLocationFromAddress(context: Context, strAddress: String): LatLng? {
    val coder = Geocoder(context)
    try {
        val addresses = coder.getFromLocationName(strAddress, 1)
        if (addresses.isNullOrEmpty()) {
            Toast.makeText(context, "Неизвестный адрес: $strAddress", Toast.LENGTH_LONG).show()
            return null
        }
        val location = addresses.firstOrNull() ?: return null
        return LatLng(location.latitude, location.longitude)
    } catch (e: Exception) {
        Log.e("MyLog", "Невозможно загрузить локацию", e)
        e.printStackTrace()
        return null
    }
}
