package com.example.myapplication.data.remote.dto.response

import java.time.LocalDateTime

data class LibrarianResponse(
    val id: Int,
    val username: String,
    val fullName : String,
    val role: String,
    val staffCode : String,
    val updatedAt: LocalDateTime,
    val deleteAt: LocalDateTime?
)