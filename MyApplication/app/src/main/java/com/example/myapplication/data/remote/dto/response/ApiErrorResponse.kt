package com.example.myapplication.data.remote.dto.response

data class ApiErrorResponse(
    val success: Boolean = false,
    val message: String,
    val data: Any? = null
)