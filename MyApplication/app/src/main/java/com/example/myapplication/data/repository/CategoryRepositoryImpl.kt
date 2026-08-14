package com.example.myapplication.data.repository

import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.network.ApiResponseHandler
import com.example.myapplication.core.network.handleNetworkException
import com.example.myapplication.core.util.requireBody
import com.example.myapplication.data.mapper.categoryMapper.toCategory
import com.example.myapplication.data.remote.api.CategoryApi
import com.example.myapplication.data.remote.dto.request.CategoryRequest
import com.example.myapplication.data.remote.dto.response.CategoryResponse
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi
) : CategoryRepository{

    override suspend fun getCategories(): Resource<List<Category>> {

        return try {
            val response = categoryApi.getCategories()
            val result = ApiResponseHandler.handle(response)
            when(result) {
                is Resource.Success -> {
                    val data = result.data
                    val categories = data.map { item ->
                        item.toCategory()
                    }
                    return Resource.Success(categories)
                }
                is Resource.Error -> return result
            }

        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }

    suspend fun getCategoryById(id: Int): CategoryResponse {
        return categoryApi.getCategoryById(id).requireBody()
    }

    suspend fun addCategory(category: CategoryRequest): CategoryResponse {
        return categoryApi.addCategory(category).requireBody()
    }

//    suspend fun updateCategory(category: CategoryRequest): CategoryResponse {
//        return categoryApi.updateCategory(category.id, category).requireBody()
//    }

    suspend fun deleteCategory(id: Int): Boolean {
        return categoryApi.deleteCategory(id).isSuccessful
    }

}