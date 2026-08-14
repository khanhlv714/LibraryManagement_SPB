package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.dto.request.LoanSlipRequest
import com.example.myapplication.data.remote.dto.request.LoginRequest
import com.example.myapplication.data.remote.dto.response.ApiResponse
import com.example.myapplication.data.remote.dto.response.LoanSlipResponse
import retrofit2.Response
import retrofit2.http.*

interface LoanSlipApi {

    @GET("api/loan-slips")
    suspend fun getLoanSlips(): Response<ApiResponse<List<LoanSlipResponse>>>

    @GET("api/loan-slips/librarian")
    suspend fun getLoanSlipsByLibrarian(): Response<ApiResponse<List<LoanSlipResponse>>>


    @GET("loan-slips/{id}")
    suspend fun getLoanSlipById(
        @Path("id") id: Int
    ): Response<LoanSlipResponse>

    @POST("api/loan-slips")
    suspend fun addLoanSlip(
        @Body loanSlip: LoanSlipRequest
    ): Response<LoanSlipResponse>

    @PUT("loan-slips/{id}")
    suspend fun updateLoanSlip(
        @Path("id") id: Int,
        @Body loanSlip: LoanSlipRequest
    ): Response<LoanSlipResponse>

    @DELETE("loan-slips/{id}")
    suspend fun deleteLoanSlip(
        @Path("id") id: Int
    ): Response<Unit>
}