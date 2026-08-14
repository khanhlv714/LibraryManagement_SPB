package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member")
data class MemberEntity(

    @PrimaryKey
    val id: Int,

    val cardNumber : String,

    val name: String,

    val accountId: Int
)