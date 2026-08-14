package com.example.myapplication.core.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

object DateJsonDeserializer : JsonDeserializer<LocalDate> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalDate {
        return LocalDate.parse(json.asString)
    }
}

object DateUtils {


    private const val SERVER_FORMAT =
        "yyyy-MM-dd'T'HH:mm:ss"


    private const val DISPLAY_FORMAT =
        "dd/MM/yyyy"



    fun formatDate(
        date:String
    ):String{


        val input = SimpleDateFormat(
            SERVER_FORMAT, Locale.getDefault()
        )


        val output =
            SimpleDateFormat(
                DISPLAY_FORMAT,
                Locale.getDefault()
            )


        return output.format(
            input.parse(date)!!
        )
    }
}