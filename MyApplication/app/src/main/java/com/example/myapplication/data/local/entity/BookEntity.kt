package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book")
data class BookEntity(

    @PrimaryKey
    val id: Int,

    val bookCode: String,

    val bookName: String,

    val price : Int,

    val categoryId: String,

    val accountId: Int
)