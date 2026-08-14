package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.dto.request.MemberRequest
import com.example.myapplication.data.remote.dto.response.ApiResponse
import com.example.myapplication.data.remote.dto.response.MemberResponse
import retrofit2.Response
import retrofit2.http.*

interface MemberApi {

    @GET("api/members")
    suspend fun getMembers(): Response<ApiResponse<List<MemberResponse>>>

    @GET("members/{id}")
    suspend fun getMemberById(
        @Path("id") id: Int
    ): Response<MemberResponse>

    @POST("members")
    suspend fun addMember(
        @Body member: MemberRequest
    ): Response<MemberResponse>

    @PUT("members/{id}")
    suspend fun updateMember(
        @Path("id") id: Int,
        @Body member: MemberRequest
    ): Response<MemberResponse>

    @DELETE("members/{id}")
    suspend fun deleteMember(
        @Path("id") id: Int
    ): Response<Unit>
}