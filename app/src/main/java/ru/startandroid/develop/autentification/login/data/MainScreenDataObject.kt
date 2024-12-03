package ru.startandroid.develop.autentification.login.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ru.startandroid.develop.autentification.authentication.AuthState

@Serializable
data class MainScreenDataObject(
    val uid: String = "",
    val email: String = ""
//    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
//    private val _authState: MutableLiveData<AuthState> = MutableLiveData<AuthState>(),
//    val authState: LiveData<AuthState> = _authState
)

//@Serializable
//object MainScreenDataObject


//object LiveDataSerializer : KSerializer<FirebaseAuth> {
//    override val descriptor = PrimitiveSerialDescriptor("AuthState", PrimitiveKind.STRING)
//
//    override fun deserialize(decoder: Decoder): FirebaseAuth {
//        return FirebaseAuth.getInstance()
//    }
//
//    override fun serialize(encoder: Encoder, value: FirebaseAuth) {
//        encoder.encodeString(value.toString())
//    }
//}




