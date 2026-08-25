package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "member")
data class MemberEntity(

    @PrimaryKey
    val id: Int,

    val cardNumber : String,

    val name: String,

    val updatedAt : LocalDateTime,

    val deleteAt : LocalDateTime?,

    val accountId: Int,

    val version : Long

)