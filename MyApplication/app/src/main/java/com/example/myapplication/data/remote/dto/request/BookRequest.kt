package com.example.myapplication.data.remote.dto.request

data class BookRequest(
    val bookCode: String,
    val bookName: String,
    val price: Int,
    val categoryId: Int,
)