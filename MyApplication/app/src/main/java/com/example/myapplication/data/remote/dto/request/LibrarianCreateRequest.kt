package com.example.myapplication.data.remote.dto.request

data class LibrarianCreateRequest(
    val username: String,

    val password: String,

    val fullName: String,

    val staffCode: String
)