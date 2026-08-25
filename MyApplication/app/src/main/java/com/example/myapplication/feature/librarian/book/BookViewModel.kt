package com.example.myapplication.feature.librarian.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.usecase.book.FilterBooksUseCase
// import com.example.myapplication.domain.usecase.book.ReFreshBooksUseCase
import com.example.myapplication.domain.usecase.category.ObserveCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookViewModel @Inject constructor(
    private val filterBooksUseCase: FilterBooksUseCase,
    private val observeCategoriesUseCase: ObserveCategoriesUseCase
) : ViewModel() {

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    private val _categoryId = MutableStateFlow<Int?>(null)
    val categoryId = _categoryId.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    private val isRefreshing = _isRefreshing.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    private val error = _error.asStateFlow()


    // =========================
    // Categories
    // =========================

    val categories: Flow<List<Category>> = observeCategoriesUseCase()


    // =========================
    // Books
    // =========================

    val bookFilter: Flow<PagingData<Book>> =
        combine(
            categoryId,
            search,
        ) { categoryId, search ->
            categoryId to search
        }
            .flatMapLatest { (categoryId, search) ->
                filterBooksUseCase(
                    search = search,
                    categoryId = categoryId
                )
            }
            .cachedIn(viewModelScope)


    // =========================
    // UI State
    // =========================

    val bookUiState =
        combine(
            error,
            isRefreshing
        ) { error, isRefreshing ->
            BookUiState(
                isRefreshing = isRefreshing,
                errorMessage = error
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(3_000),
            BookUiState(
                isRefreshing = false,
                errorMessage = null
            )
        )


    // =========================
    // Search
    // =========================

    fun search(value: String) {
        _search.value = value
    }


    // =========================
    // Category
    // =========================

    fun setCategory(categoryId: Int?) {
        _categoryId.value = categoryId
    }


    // =========================
    // Refresh
    // =========================

//    fun refreshFromServer() {
//
//        viewModelScope.launch {
//
//            _isRefreshing.value = true
//            _error.value = null
//
//            when (val result = reFreshBookUseCase()) {
//
//                is Resource.Success -> {
//                    _isRefreshing.value = false
//                }
//
//                is Resource.Error -> {
//                    _isRefreshing.value = false
//                    _error.value = result.message
//                }
//            }
//        }
//    }
}