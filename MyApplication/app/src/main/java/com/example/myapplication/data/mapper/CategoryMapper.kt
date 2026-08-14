package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.dto.response.BookResponse
import com.example.myapplication.data.remote.dto.response.CategoryResponse
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Category

object categoryMapper {
    fun CategoryResponse.toCategory(): Category {
        return Category(id,categoryCode,categoryName,amountBook)
    }
}