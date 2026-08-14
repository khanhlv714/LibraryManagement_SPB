package com.example.myapplication.feature.librarian.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.usecase.member.GetMembersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val getMembersUseCase: GetMembersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MemberUiState())
    val uiState = _uiState.asStateFlow()

    fun loadMembers() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    loading = true, error = null
                )
            }

            when (val result = getMembersUseCase()) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            loading = false, error = null, memberList = result.data
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(loading = false, error  = result.message);
                    }
                }
            }
        }
    }
}