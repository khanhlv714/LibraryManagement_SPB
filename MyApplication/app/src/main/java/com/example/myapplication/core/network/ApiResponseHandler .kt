package com.example.myapplication.core.network

import com.example.myapplication.core.common.Resource
import com.example.myapplication.data.remote.dto.response.ApiErrorResponse
import com.example.myapplication.data.remote.dto.response.ApiResponse
import com.google.gson.Gson
import retrofit2.Response

// can throw an exception
object ApiResponseHandler {
    fun <R> handle(response : Response<ApiResponse<R>>): Resource<R> {

        if (!response.isSuccessful) {

            val errorBody = response.errorBody()?.string()

            val errorResponse = Gson().fromJson(
                errorBody, ApiErrorResponse::class.java
            )

            return Resource.Error(errorResponse.message)
        }

        val apiResponse = response.body() ?: return Resource.Error("Empty response")

        if (!apiResponse.success) {
            return Resource.Error(apiResponse.message)
        }
        val data = apiResponse.data ?: return Resource.Error("No data")

        return Resource.Success(data);
    }
}