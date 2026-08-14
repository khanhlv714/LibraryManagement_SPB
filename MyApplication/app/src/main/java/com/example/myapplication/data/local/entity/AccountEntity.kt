package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "account")
data class AccountEntity(
    @PrimaryKey
    val id: Int,

    val username: String,

    val password: String,

    val fullName : String,

    val staffCode : String
)