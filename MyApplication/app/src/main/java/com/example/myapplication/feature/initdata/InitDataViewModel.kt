package com.example.myapplication.feature.initdata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.usecase.SyncDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitDataViewModel @Inject constructor(
    private val syncDataUseCase: SyncDataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<Unit>?>(null)
    val state: StateFlow<Resource<Unit>?> = _state.asStateFlow()

    fun sync() {
        viewModelScope.launch {
            _state.value = syncDataUseCase.syncData()
        }
    }
}