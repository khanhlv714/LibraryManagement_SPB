package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime


@Entity(tableName = "loanSlip")
data class LoanSlipEntity(

    @PrimaryKey
    val id: Int,

    val receiptNumber : String,

    val accountId: Int,

    val bookId: Int,
    
    val memberId: Int,

    val states : Int,

    val updatedAt : LocalDateTime,

    val deleteAt : LocalDateTime?,

    val borrowDate : LocalDate,

    val dueDate : LocalDate,

    val version : Long


)