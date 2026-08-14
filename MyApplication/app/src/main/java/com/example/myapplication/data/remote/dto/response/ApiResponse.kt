package com.example.myapplication.data.remote.dto.response

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T?
)