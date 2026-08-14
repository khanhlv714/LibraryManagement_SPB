package com.example.myapplication.data.remote.dto.response

import com.example.myapplication.domain.model.Account

data class CategoryResponse(
    val id: Int,
    val categoryCode: String,
    val categoryName: String,
    val createdBy: Account,
    val amountBook : Int
)
