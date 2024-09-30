package ru.startandroid.develop.autentification

import android.app.Application
import ru.startandroid.develop.autentification.roomdb.data.MainDb

class App : Application() {
    val database by lazy { MainDb.createDataBase(this) }
}