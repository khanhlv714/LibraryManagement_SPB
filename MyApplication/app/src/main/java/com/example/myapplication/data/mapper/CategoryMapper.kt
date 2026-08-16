package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.CategoryEntity
import com.example.myapplication.data.remote.dto.response.BookResponse
import com.example.myapplication.data.remote.dto.response.CategoryResponse
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Category

object categoryMapper {
    fun CategoryResponse.toCategory(): Category {
        return Category(id,categoryCode,categoryName,amountBook)
    }
    fun CategoryResponse.toEntiry(): CategoryEntity {
        return CategoryEntity(id,categoryCode,categoryName,createdBy.id)
    }
}