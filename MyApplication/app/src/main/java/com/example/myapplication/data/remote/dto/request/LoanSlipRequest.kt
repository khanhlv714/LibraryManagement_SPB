package com.example.myapplication.data.remote.dto.request

import java.time.LocalDate


data class LoanSlipRequest(
     val receiptNumber: String ,

     val bookId: Int,

     val memberId: Int,

     val state: Int,

     val borrowDate: LocalDate,

     val dueDate: LocalDate)
