package com.example.myapplication.domain.usecase

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.repository.SyncRepository
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(val repository : SyncRepository){
    suspend fun syncData(): Resource<Unit> {
        return repository.syncData()
    }
}