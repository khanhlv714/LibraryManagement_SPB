package com.example.myapplication.domain.repository

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.LoanSlip

interface LoanSlipRepository {

    suspend fun getLoanSlips(): Resource<List<LoanSlip>>

//    suspend fun getLoanSlipById(id: Int): LoanSlipResponse
//
//    suspend fun createLoan(
//        loanSlip: LoanSlipRequest
//    ): LoanSlipResponse

    // suspend fun updateLoan(
    //     loanSlip: LoanSlipRequest
    // ): LoanSlipResponse
}