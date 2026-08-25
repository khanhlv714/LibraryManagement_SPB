package com.example.myapplication.data.remote.dto.response

import java.time.LocalDateTime

data class MemberSyncResponse(
    val id: Int,
    val cardNumber: String,
    val name: String,
    val createdBy: Int,
    val updatedAt: LocalDateTime,
    val version : Long,
    val deleteAt: LocalDateTime?
)