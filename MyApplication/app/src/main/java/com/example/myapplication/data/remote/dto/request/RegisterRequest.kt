package com.example.myapplication.data.remote.dto.request

data class RegisterRequest(
    
    val username: String,

    val password: String,

    val fullName: String,

    val role: String,

    val staffCode: String
)