package ru.startandroid.develop.autentification.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import ru.startandroid.develop.autentification.login.data.MainScreenDataObject

class AuthViewModel : ViewModel()  {
    private val auth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun signIn(
        email : String,
        password : String,
        onSignInSuccess: (MainScreenDataObject) -> Unit
    ){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task->
                if (task.isSuccessful){
                    onSignInSuccess(
                        MainScreenDataObject(
                            task.result.user?.uid!!,
                            task.result.user?.email!!
                        )
                    )
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signUp(
        email : String,
        password : String,
        onSignUpSuccess: (MainScreenDataObject) -> Unit
    ){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task->
                if (task.isSuccessful){
                    onSignUpSuccess(
                        MainScreenDataObject(
                            task.result.user?.uid!!,
                            task.result.user?.email!!
                        )
                    )
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}
