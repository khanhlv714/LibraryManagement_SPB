package com.example.myapplication.data.remote.dto.response

import java.time.LocalDate
import java.time.LocalDateTime


data class LoanSlipResponse(
     val id: Int,
     val receiptNumber: String,
     val createdByAccountId: Int,
     val createdByUsername: String,
     val bookId: Int,
     val bookName: String,
     val bookCode: String,
     val memberId: Int,
     val memberName: String,
     val memberCardNumber: String,
     val state: Int,
     val borrowDate: LocalDate,
     val dueDate: LocalDate,
     val updatedAt: LocalDateTime,
     val deleteAt: LocalDateTime?
)