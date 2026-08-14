package com.example.myapplication.data.repository

import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.network.ApiResponseHandler
import com.example.myapplication.core.network.handleNetworkException
import com.example.myapplication.data.mapper.LoanSlipMapper.toLoanSlip
import com.example.myapplication.data.mapper.MemberMapper.toMember
import com.example.myapplication.data.remote.api.LoanSlipApi
import com.example.myapplication.data.remote.api.MemberApi
import com.example.myapplication.domain.model.LoanSlip
import com.example.myapplication.domain.model.Member
import com.example.myapplication.domain.repository.LoanSlipRepository
import com.example.myapplication.feature.librarian.loanslip.LoanSlipAdapter
import javax.inject.Inject

class LoanSlipRepositoryImpl @Inject constructor(
    private val loanSlipApi: LoanSlipApi
) : LoanSlipRepository{

    override suspend fun getLoanSlips(): Resource<List<LoanSlip>> {
        return try {
            val res = loanSlipApi.getLoanSlipsByLibrarian()
            val result = ApiResponseHandler.handle(res)
            when(result) {
                is Resource.Success -> {
                    val data = result.data

                    val loanSlips = data.map { item ->
                        item.toLoanSlip()
                    }
                    return Resource.Success(loanSlips)
                }
                is Resource.Error -> return result
            }

        }catch(e: Exception) {
            handleNetworkException(e)
        }
    }
}
