package com.example.myapplication.data.remote.dto.response

import java.time.LocalDateTime

data class CategorySyncResponse(
    val id: Int,
    val categoryCode: String,
    val categoryName: String,
    val createdBy: Int,
    val version : Long,
    val updatedAt: LocalDateTime,
    val deleteAt: LocalDateTime?
)