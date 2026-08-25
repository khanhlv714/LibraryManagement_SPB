package com.example.myapplication.data.remote.dto.response

import java.time.LocalDate
import java.time.LocalDateTime

data class LoanSlipSyncResponse(
    val id: Int,
    val receiptNumber: String,
    val accountId: Int,
    val bookId: Int,
    val memberId: Int,
    val state: Int,
    val version : Long,
    val borrowDate: LocalDate,
    val dueDate: LocalDate,
    val updatedAt: LocalDateTime,
    val deleteAt: LocalDateTime?
)