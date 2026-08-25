package com.example.myapplication.domain.usecase.category

import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
     operator fun invoke(): Flow<List<Category>> {
        return repository.observeCategories()
    }
}