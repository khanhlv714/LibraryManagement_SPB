package com.example.myapplication.data.remote.dto.response

import java.time.LocalDateTime

data class BookSyncResponse(
    val id: Int,
    val bookCode: String,
    val bookName: String,
    val price: Int,
    val createdBy: Int,
    val version : Long,
    val categoryId: Int,
    val updatedAt: LocalDateTime,
    val deleteAt: LocalDateTime?
)