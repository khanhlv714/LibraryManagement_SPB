package com.example.myapplication.domain.model

data class Book(
    val id: Int,
    val bookCode: String,
    val bookName: String,
    val price: Int,
    val categoryId: Int
)