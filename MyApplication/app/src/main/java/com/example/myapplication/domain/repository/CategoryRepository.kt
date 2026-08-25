package com.example.myapplication.domain.repository

import com.example.myapplication.core.common.Resource
import com.example.myapplication.data.remote.api.CategoryApi
import com.example.myapplication.data.remote.dto.response.ApiResponse
import com.example.myapplication.data.remote.dto.response.CategoryResponse
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.model.CategoryWithBookCount
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CategoryRepository{
     fun getLocalCategories(): Flow<List<CategoryWithBookCount>>

    fun observeCategories(): Flow<List<Category>>

//    suspend fun refreshCategories(): Resource<Unit>
}