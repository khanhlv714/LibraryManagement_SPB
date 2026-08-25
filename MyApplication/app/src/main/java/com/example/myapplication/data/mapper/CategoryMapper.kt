package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.CategoryEntity
import com.example.myapplication.data.remote.dto.response.CategoryResponse
import com.example.myapplication.data.remote.dto.response.CategorySyncResponse
import com.example.myapplication.domain.model.Category

object categoryMapper {
    fun CategoryResponse.toCategory(): Category {
        return Category(id,categoryCode,categoryName)
    }
//    fun CategoryResponse.toEntiry(): CategoryEntity {
//        return CategoryEntity(id,categoryCode  ,categoryName,updatedAt,deleteAt,accountId)
//    }
    fun CategorySyncResponse.toEntity() = CategoryEntity(
        id = id,
        categoryCode = categoryCode,
        categoryName = categoryName,
        updatedAt = updatedAt,
        deleteAt = deleteAt,
        accountId = createdBy ,
        version
    )

    fun CategoryEntity.toCategory(): Category {
        return Category(id,categoryCode,categoryName)
    }

}