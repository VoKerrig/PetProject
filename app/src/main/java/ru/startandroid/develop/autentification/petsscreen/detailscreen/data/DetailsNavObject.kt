package ru.startandroid.develop.autentification.petsscreen.detailscreen.data

import kotlinx.serialization.Serializable

@Serializable
data class DetailsNavObject(
    val name: String = "",
    val breed: String = "",
    val age: String = "",
    val pol: String = "",
    val imageUrl: String = ""
)
