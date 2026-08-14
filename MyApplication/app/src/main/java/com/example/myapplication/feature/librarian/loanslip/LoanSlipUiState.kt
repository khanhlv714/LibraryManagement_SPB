package com.example.myapplication.feature.librarian.loanslip

import com.example.myapplication.domain.model.LoanSlip

data class LoanSlipUiState(
    val loading : Boolean  = false,
    val error : String? = null,
    val data : List<LoanSlip> = listOf()
)