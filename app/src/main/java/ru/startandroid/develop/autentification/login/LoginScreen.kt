package ru.startandroid.develop.autentification.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import ru.startandroid.develop.autentification.R
import ru.startandroid.develop.autentification.Routs
import ru.startandroid.develop.autentification.authentication.AuthState
import ru.startandroid.develop.autentification.authentication.AuthViewModel
import ru.startandroid.develop.autentification.login.data.MainScreenDataObject
import ru.startandroid.develop.autentification.login.data.SignUpScreenObject
import ru.startandroid.develop.autentification.ui.theme.BarColor

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    onNavigateToMainScreen: (MainScreenDataObject) -> Unit
) {

//    val auth = remember {
//        Firebase.auth
//    }
    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate(MainScreenDataObject)
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .background(BarColor)
                .padding(start = 40.dp, end = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            EmailTextField(
                text = emailState,
                label = "mail@example.com"
            ) {
                emailState = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextField(
                text = passwordState,
                label = "Password"
            ) {
                passwordState = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                authViewModel.signIn(
                    emailState,
                    passwordState,
//                    onSignInSuccess = { navData ->
//                        onNavigateToMainScreen(navData)
//                    }
                )
            }) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(onClick = {
                navController.navigate(SignUpScreenObject)
            }) {
                Text(text = "Нет аккаунта? Зарегистрируйтесь")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BarColor),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                modifier = Modifier.size(350.dp),
                painter = painterResource(id = R.drawable.auth_pic),
                contentDescription = "background",
            )
        }
    }
}
