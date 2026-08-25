package com.example.myapplication.data.remote.dto.response

import java.time.LocalDateTime

data class MemberResponse(
    val id : Int,
    val cardNumber : String,
    val name : String,
    val createdByUsername : String,
    val createdById : Int,
    val updatedAt: LocalDateTime,
    val deleteAt: LocalDateTime?
)
