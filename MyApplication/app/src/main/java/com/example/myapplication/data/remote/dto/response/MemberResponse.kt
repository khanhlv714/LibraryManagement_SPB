package com.example.myapplication.data.remote.dto.response

data class MemberResponse(
    val id : Int,
    val cardNumber : String,
    val name : String,
    val createdByUsername : String,
    val createdById : Int
)
