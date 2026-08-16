package com.example.myapplication

import android.app.Application
import com.example.myapplication.core.datastore.TokenManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class LibraryApplication : Application() {

    @Inject
    lateinit var tokenManager : TokenManager
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            tokenManager.loadToken()
        }

    }
}

//override fun observeTop40(): Flow<List<Book>> {
//    return dao.observeTop40()
//        .map { entities ->
//            entities.map { it.toDomain() }
//        }
//}
//
//override suspend fun refreshTop40() {
//    val response = api.getTop40()
//
//    dao.replaceAll(
//        response.map { it.toEntity() }
//    )
//}
//
//
//@HiltViewModel
//class BookViewModel @Inject constructor(
//    private val repository: BookRepository,
//    private val networkMonitor: NetworkMonitor
//) : ViewModel() {
//
//    private val search = MutableStateFlow("")
//
//    private val books =
//        repository.observeTop40()
//
//    val uiState: StateFlow<BookUiState> =
//        combine(
//            search,
//            books
//        ) { searchText, books ->
//
//            val filteredBooks =
//                if (searchText.isBlank()) {
//                    books
//                } else {
//                    books.filter {
//                        it.title.contains(
//                            searchText,
//                            ignoreCase = true
//                        )
//                    }
//                }
//
//            BookUiState(
//                search = searchText,
//                books = filteredBooks
//            )
//
//        }.stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5_000),
//            BookUiState()
//        )
//
//    init {
//        observeNetwork()
//    }
//
//    fun onSearchChanged(value: String) {
//        search.value = value
//    }
//
//    private fun observeNetwork() {
//
//        viewModelScope.launch {
//
//            networkMonitor.isOnline
//                .collect { online ->
//
//                    if (online) {
//                        refresh()
//                    }
//                }
//        }
//    }
//
//    private suspend fun refresh() {
//
//        try {
//
//            repository.refreshTop40()
//
//        } catch (e: Exception) {
//
//            // Không làm mất dữ liệu Room
//            // UI vẫn dùng dữ liệu local
//        }
//    }
//}