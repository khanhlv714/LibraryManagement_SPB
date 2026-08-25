package com.example.myapplication.feature.librarian.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.datastore.SessionManager
import com.example.myapplication.core.network.NetworkMonitor
import com.example.myapplication.domain.usecase.SyncDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import javax.inject.Inject

@HiltViewModel
class LibrarianHomeViewModel @Inject constructor(
    val syncDataUseCase: SyncDataUseCase, val networkMonitor: NetworkMonitor
) : ViewModel() {

    fun registerChangeNetworkListener() {
        viewModelScope.launch {
            networkMonitor.isOnline.collect { isOnline ->
                if (isOnline && networkMonitor.isSynced == false) {
                    sync()
                }
            }
        }
    }

    fun manualSync() {
         sync();
    }

    fun sync() {
        viewModelScope.launch {
            val result = syncDataUseCase.syncData()
            if (result is Resource.Success) {
                networkMonitor.markSynced()
            }
        }
    }
}