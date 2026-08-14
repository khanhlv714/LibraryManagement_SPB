package com.example.myapplication.domain.model

import java.time.LocalDate

data class LoanSlip(

    val id: Int,

    val receiptNumber : String,

    val bookName : String,

    val bookCode : String,

    val memberName : String,

    val memberCardNumber : String,

    val state : Int,

    val borrowDate: String,

    val dueDate: String,
)