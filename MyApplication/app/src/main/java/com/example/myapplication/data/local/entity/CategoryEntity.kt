package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category")
data class CategoryEntity(

    @PrimaryKey
    val id: Int,

    val categoryCode: String,

    val categoryName: String,

    val accountId: Int
)