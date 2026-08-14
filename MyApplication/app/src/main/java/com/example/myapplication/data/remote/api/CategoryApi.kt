package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.dto.request.CategoryRequest
import com.example.myapplication.data.remote.dto.response.ApiResponse
import com.example.myapplication.data.remote.dto.response.CategoryResponse
import retrofit2.Response
import retrofit2.http.*

interface CategoryApi {

    @GET("api/categories")
    suspend fun getCategories(): Response<ApiResponse<List<CategoryResponse>>>

    @GET("categories/{id}")
    suspend fun getCategoryById(
        @Path("id") id: Int
    ): Response<CategoryResponse>

    @POST("categories")
    suspend fun addCategory(
        @Body category: CategoryRequest
    ): Response<CategoryResponse>

    @PUT("categories/{id}")
    suspend fun updateCategory(
        @Path("id") id: Int,
        @Body category: CategoryRequest
    ): Response<CategoryResponse>

    @DELETE("categories/{id}")
    suspend fun deleteCategory(
        @Path("id") id: Int
    ): Response<Unit>
}