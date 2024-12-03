package ru.startandroid.develop.autentification.login.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement

//object MainScreenDataObjectSerializer: KSerializer<MainScreenDataObject> {
//    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
//        serialName = MainScreenDataObject::class.simpleName!!
//    ){
//        element<String?>("uid")
//        element<String?>("email")
//    }
//
//    override fun deserialize(decoder: Decoder): MainScreenDataObject = decoder.
//    decodeStructure(descriptor) {
//        var uid: String? = null
//
//        while (true) {
//            when(val index = decodeElementIndex(descriptor)) {
//                0 -> {
//                    val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException(
//                        "only with JSON"
//                    )
//                    val element = jsonDecoder.decodeJsonElement()
//                    descriptor = if ( element is JsonPrimitive) {
//                                element.content
//                    }
//                }
//                1 -> {
//
//                }
//                CompositeDecoder.DECODE_DONE -> break
//                else -> throw SerializationException("Invalid index")
//            }
//        }
//
//        return MainScreenDataObject(uid)
//    }
//
//    override fun serialize(encoder: Encoder, value: MainScreenDataObject) {
//        TODO("Not yet implemented")
//    }
//}