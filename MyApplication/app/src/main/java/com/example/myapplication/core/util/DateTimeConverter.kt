package com.example.myapplication.core.util

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime

class DateTimeConverter {

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): String {
        return value.toString()
    }

    @TypeConverter
    fun toLocalDateTime(value: String): LocalDateTime {
        return LocalDateTime.parse(value)
    }

    @TypeConverter
    fun fromLocalTime(value: LocalDate): String {
        return value.toString()
    }

    @TypeConverter
    fun toLocalTime(value: String): LocalDate {
        return LocalDate.parse(value)
    }

}