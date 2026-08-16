package com.example.myapplication.data.remote.dto.response

data class BookResponse(
    val id: Int,
    val bookCode: String,
    val bookName: String,
    val price: Int,
    val categoryName: String,
    val createdBy: String,
    val accountId : Int,
    val categoryId : Int
)