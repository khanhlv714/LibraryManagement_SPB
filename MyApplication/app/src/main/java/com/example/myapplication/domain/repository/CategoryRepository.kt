package com.example.myapplication.domain.repository

import com.example.myapplication.core.common.Resource
import com.example.myapplication.data.remote.api.CategoryApi
import com.example.myapplication.domain.model.Category

interface CategoryRepository{
    suspend fun getCategories(): Resource<List<Category>>

}