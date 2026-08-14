package com.example.myapplication.core.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDate

enum class UserRole {
    ADMIN,
    LIBRARIAN
}

object Constants {

    const val BASE_URL = "https://api.library.com/"

    const val DATABASE_NAME = "library_database"

    const val TIMEOUT = 30L

    const val PREF_TOKEN = "access_token"

    val ROLE_ADMIN = "ADMIN"

    val ROLE_LIBRARIAN = "LIBRARIAN"

}