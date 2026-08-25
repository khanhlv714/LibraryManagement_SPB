package com.example.myapplication.feature.librarian.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.usecase.category.ObserveCategoriesUseCase
import com.example.myapplication.domain.usecase.category.RefreshCategoriesUseCase
import com.example.myapplication.feature.librarian.book.BookUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
         private val getCategoriesUseCase: ObserveCategoriesUseCase,
         private val refreshCategoriesUseCase: RefreshCategoriesUseCase
) : ViewModel() {
    private val categoriesList = getCategoriesUseCase()

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    private val isRefreshing = _isRefreshing.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    private val error = _error.asStateFlow()


    val uiState: StateFlow<CategoryUiState> = combine(categoriesList, search,isRefreshing,error){ categoriesList, search,isRefreshing,error ->
            var categoriesUi = categoriesList
            if (search.isNullOrBlank() == false){
                categoriesUi = categoriesUi.filter {
                    it.categoryCode.contains(search,false)
                            || it.categoryCode.contains(search,false)
                }
            }
            CategoryUiState(listOf(), isRefreshing, error);
        }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000), CategoryUiState()
        )

    fun refreshFromServer(){
        viewModelScope.launch {
            _isRefreshing.value = true
            _error.value = null
            ////             ////
            val result : Resource<Unit> = refreshCategoriesUseCase()
            if(result is Resource.Success){
                _isRefreshing.value = false
                _error.value = null
            }
            if(result is Resource.Error){
                _isRefreshing.value = false
                _error.value = result.message
            }
        }
    }
}