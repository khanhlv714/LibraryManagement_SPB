package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "book")
data class BookEntity(

    @PrimaryKey
    val id: Int,

    val bookCode: String,

    val bookName: String,

    val price : Int,

    val categoryId: Int,

    val updatedAt : LocalDateTime,

    val deleteAt : LocalDateTime?,

    val accountId: Int,

    val version : Long

)