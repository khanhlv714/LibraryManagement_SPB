package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.dto.response.ApiResponse
import com.example.myapplication.data.remote.dto.response.MemberResponse
import com.example.myapplication.data.remote.dto.response.SyncDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SyncApi{
    @GET("api/sync/since-{time}")
    suspend fun syncDataToRoom(@Path("time") time : String): Response<ApiResponse<SyncDataResponse>>


    @GET("api/sync/init-data")
    suspend fun initDataBaseRoom(): Response<ApiResponse<SyncDataResponse>>
}