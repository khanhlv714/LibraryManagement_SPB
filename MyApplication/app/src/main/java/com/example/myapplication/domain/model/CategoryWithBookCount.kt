package com.example.myapplication.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.example.myapplication.data.local.entity.CategoryEntity

data class CategoryWithBookCount(
    @Embedded
    val category: CategoryEntity,

    @ColumnInfo(name = "amountBook")
    val amountBook: Int
)