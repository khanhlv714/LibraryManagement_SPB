package com.example.myapplication.feature.librarian.loanslip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.usecase.loanslip.GetLoanSlipsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoanSlipViewModel @Inject constructor(val getLoanSlipsUseCase: GetLoanSlipsUseCase) : ViewModel() {

    private var _uiState = MutableStateFlow(LoanSlipUiState())
    val uiState : StateFlow<LoanSlipUiState>
        get() = _uiState.asStateFlow()


    fun loadCategories() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    loading = true, error = null
                )
            }

            when (val result = getLoanSlipsUseCase()) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            loading = false, error = null, data = result.data
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(loading = false, error = null);
                    }
                }
            }
        }
    }


}