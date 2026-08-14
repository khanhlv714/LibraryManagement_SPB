package com.example.myapplication.data.remote.dto.request

data class MemberRequest(
    val cardNumber : String,
    val name : String,
    val createdBy : Int,
)