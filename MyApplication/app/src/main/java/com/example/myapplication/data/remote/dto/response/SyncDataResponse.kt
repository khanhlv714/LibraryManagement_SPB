package com.example.myapplication.data.remote.dto.response

import com.example.myapplication.data.local.entity.BookEntity
import com.example.myapplication.data.local.entity.CategoryEntity
import com.example.myapplication.data.local.entity.LoanSlipEntity
import com.example.myapplication.data.local.entity.MemberEntity
import java.time.LocalDateTime
import java.time.LocalTime

data class SyncDataResponse(
    val books : List<BookSyncResponse>,
    val categories : List<CategorySyncResponse>,
    val members : List<MemberSyncResponse>,
    val loanSlips : List<LoanSlipSyncResponse>,
    val timeSync : LocalDateTime
)