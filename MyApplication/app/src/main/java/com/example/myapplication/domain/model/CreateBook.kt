package com.example.myapplication.domain.model

data class CreateBook(
    val bookCode: String,
    val bookName: String,
    val price: Int,
    val categoryId: Int
)