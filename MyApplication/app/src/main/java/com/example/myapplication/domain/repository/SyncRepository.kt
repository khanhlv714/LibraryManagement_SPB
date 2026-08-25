package com.example.myapplication.domain.repository

import com.example.myapplication.core.common.Resource

interface SyncRepository {
    suspend fun syncData() : Resource<Unit>
}