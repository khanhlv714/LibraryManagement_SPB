package com.example.myapplication.data.repository

import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.network.ApiResponseHandler
import com.example.myapplication.core.network.handleNetworkException
import com.example.myapplication.core.util.requireBody
import com.example.myapplication.data.local.dao.CategoryDao
import com.example.myapplication.data.mapper.bookMapper.toBook
import com.example.myapplication.data.mapper.categoryMapper.toCategory
import com.example.myapplication.data.remote.api.CategoryApi
import com.example.myapplication.data.remote.dto.request.CategoryRequest
import com.example.myapplication.data.remote.dto.response.ApiResponse
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.model.CategoryWithBookCount
import com.example.myapplication.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import okhttp3.Response
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi,
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun getLocalCategories(): Flow<List<CategoryWithBookCount>> {
        TODO("Not yet implemented")
    }

    override fun observeCategories(): Flow<List<Category>> {
        return categoryDao.observeCategories()
            .map { list ->
                list.map { it.toCategory() }
            }
    }


//    override fun getLocalCategories(): Flow<List<CategoryWithBookCount>> {
//        return categoryDao.getCategoriesWithBookCount()
//    }
//
//    override suspend fun refreshCategories(): Resource<Unit>{
//        return try {
//            val response = categoryApi.getCategories()
//
//            when (val result = ApiResponseHandler.handle(response)) {
//
//                is Resource.Success -> {
//                    val categories = result.data.map{
//                        it.toEntiry()
//                    }
//
//                    categoryDao.insertCategories(categories)
//
//                    Resource.Success(Unit)
//                }
//
//                is Resource.Error -> {
//                    Resource.Error(
//                        message = result.message
//                    )
//                }
//            }
//
//        } catch (e: Exception) {
//            handleNetworkException(e)
//        }
//    }
}




//    suspend fun getCategoryById(id: Int): CategoryResponse {
//        return categoryApi.getCategoryById(id).requireBody()
//    }

//    suspend fun addCategory(category: CategoryRequest): CategoryResponse {
//        return categoryApi.addCategory(category).requireBody()
//    }

//    suspend fun updateCategory(category: CategoryRequest): CategoryResponse {
//        return categoryApi.updateCategory(category.id, category).requireBody()
//    }

//    suspend fun deleteCategory(id: Int): Boolean {
//        return categoryApi.deleteCategory(id).isSuccessful
//    }

