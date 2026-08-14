package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "loanSlip")
data class LoanSlipEntity(

    @PrimaryKey
    val id: Int,

    val receiptNumber : String,

    val accountId: Int,

    val bookId: Int,

    val memberId: Int,

    val states : Int,

    val borrowDate : String,

    val dueDate : String


)