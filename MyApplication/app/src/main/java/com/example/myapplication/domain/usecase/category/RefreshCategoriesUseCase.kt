package com.example.myapplication.domain.usecase.category

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.CategoryWithBookCount
import com.example.myapplication.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RefreshCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): Resource<Unit> {
        return Resource.Success(Unit)
    }
}