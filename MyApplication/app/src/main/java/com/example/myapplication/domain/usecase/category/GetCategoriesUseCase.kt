package com.example.myapplication.domain.usecase.category

import com.example.myapplication.core.common.Resource
import com.example.myapplication.data.repository.CategoryRepositoryImpl
import com.example.myapplication.domain.model.Category
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepositoryImpl
) {
    suspend operator fun invoke(): Resource<List<Category>> {
        return repository.getCategories()
    }
}