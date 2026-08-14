package com.example.myapplication.domain.usecase.loanslip

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.repository.LoanSlipRepository
import com.example.myapplication.domain.model.LoanSlip
import javax.inject.Inject

class GetLoanSlipsUseCase @Inject constructor(
    private val repository: LoanSlipRepository
) {
    suspend operator fun invoke(): Resource<List<LoanSlip>> {
        return repository.getLoanSlips()
    }
}