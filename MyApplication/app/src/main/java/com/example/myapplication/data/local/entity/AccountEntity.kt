package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "account")
data class AccountEntity(
    @PrimaryKey
    val id: Int,

    val username: String,

    val password: String,

    val fullName : String,

    val updatedAt : LocalDateTime,

    val deleteAt : LocalDateTime?,

    val staffCode : String ,

    val version : Long
)