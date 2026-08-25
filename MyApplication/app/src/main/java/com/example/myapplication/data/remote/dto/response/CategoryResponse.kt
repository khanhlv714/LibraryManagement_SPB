package com.example.myapplication.data.remote.dto.response

import com.example.myapplication.domain.model.Account
import java.time.LocalDateTime

//package com.example.myapplication.data.remote.dto.response
//
//import com.example.myapplication.domain.model.Account
//import java.time.LocalDateTime

data class CategoryResponse(
    val id: Int,
    val categoryCode: String,
    val categoryName: String,
    val accountId: Int,
    val amountBook: Long,
    val updatedAt: LocalDateTime,
    val deleteAt: LocalDateTime?
)