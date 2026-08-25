package com.example.myapplication.domain.usecase.book

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.map
import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.repository.BookRepository
import com.example.myapplication.feature.librarian.book.BookFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZoneOffset
import javax.inject.Inject

//class GetFilteredLocalBookUseCase @Inject constructor(
//    private val repository: BookRepository
//) {
//     suspend fun filteredBooks(bookFilter: BookFilter,offset: Int): List<Book> {
//        return repository.filterBooks(bookFilter,offset)
//    }
//}