package com.example.myapplication.data.remote.dto.response

import java.time.LocalDate


data class LoanSlipResponse(
     val id: Int,

     val receiptNumber: String,

     val createdByAccountId: Int,

     val createdByUsername: String,

     val bookId: Int,

     val bookCode : String,

     val bookName: String,

     val memberId: Int,

     val memberName: String,

     val memberCardNumber : String,

     val state: Int,

     val borrowDate: LocalDate,

     val dueDate: LocalDate,
)