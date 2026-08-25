package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "category")
data class CategoryEntity(

    @PrimaryKey
    val id: Int,

    val categoryCode: String,
    
    val categoryName: String,

    val updatedAt : LocalDateTime,

    val deleteAt : LocalDateTime?,

    val accountId: Int,

    val version : Long

)