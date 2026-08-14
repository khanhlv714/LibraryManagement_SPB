package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.dto.request.LoginRequest
import com.example.myapplication.data.remote.dto.request.RefreshTokenRequest
import com.example.myapplication.data.remote.dto.response.ApiResponse
import com.example.myapplication.data.remote.dto.response.LoginResponse
import com.example.myapplication.data.remote.dto.response.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<ApiResponse<LoginResponse>>


    @POST("api/auth/refresh-token")
    suspend fun refreshToken(
        @Body request: RefreshTokenRequest
    ): Response<ApiResponse<RefreshTokenResponse>>
}