package com.example.myapplication.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import androidx.room.Query
import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.network.ApiResponseHandler
import com.example.myapplication.core.network.handleNetworkException
import com.example.myapplication.core.util.requireBody
import com.example.myapplication.data.local.dao.BookDao
import com.example.myapplication.data.local.entity.BookEntity
import com.example.myapplication.data.mapper.bookMapper.toBook
import com.example.myapplication.data.remote.api.BookApi
import com.example.myapplication.data.remote.dto.request.BookRequest
import com.example.myapplication.data.remote.dto.response.ApiErrorResponse
import com.example.myapplication.data.remote.dto.response.BookResponse
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.repository.BookRepository
import com.example.myapplication.feature.librarian.book.BookFilter
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.time.ZoneOffset
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookApi: BookApi, private val bookDao: BookDao
) : BookRepository {

    override fun filterBooks(
        search: String,
        categoryId: Int?
    ): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30
            ),
            pagingSourceFactory = {
                bookDao.observeFilteredBooks(
                    search = search,
                    categoryId = categoryId
                )
            }
        ).flow
            .map { pagingData ->
                pagingData.map { entity ->
                    entity.toBook()
                }
            }
    }


    override suspend fun getBooks(): Resource<List<Book>> {
        return try {
            val res = bookApi.getBooks()
            when (val result = ApiResponseHandler.handle(res)) {
                is Resource.Success -> {
                    val data = result.data

                    val books = data.map { item ->
                        item.toBook()
                    }
                    return Resource.Success(books)
                }

                is Resource.Error -> return result
            }

        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }


//override suspend fun refreshLocal(): Resource<Unit> {
//    return try {
//        val response = bookApi.getBooks()
//
//        when (val result = ApiResponseHandler.handle(response)) {
//
//            is Resource.Success -> {
//                val books = result.data.map { it.toEntiry() }
//
//                bookDao.insertBooks(books)
//
//                Resource.Success(Unit)
//            }
//
//            is Resource.Error -> {
//                Resource.Error(
//                    message = result.message
//                )
//            }
//        }
//
//    } catch (e: Exception) {
//        handleNetworkException(e)
//    }
//}

//override suspend fun reFreshBooksFromServer(): Resource<Unit> {
//    return try {
//        val res = bookApi.getBooks()
//        val result = ApiResponseHandler.handle(res)
//        when (result) {
//            is Resource.Success -> {
//                val data = result.data
//
//                val books = data.map { item ->
//                    item.toEntiry()
//                }
//
//                return Resource.Success(Unit)
//            }
//
//            is Resource.Error -> return result
//        }
//
//    } catch (e: Exception) {
//        handleNetworkException(e)
//    }
//}


suspend fun getBookById(id: Int): BookResponse {
    return bookApi.getBookById(id).requireBody()
}

suspend fun addBook(book: BookRequest): BookResponse {
    return bookApi.addBook(book).requireBody()
}

//    suspend fun updateBook(book: BookRequest): BookResponse {
//        return bookApi.updateBook(book.id, book).requireBody()
//    }
//
//suspend fun deleteBook(id: Int): Boolean {
//    return bookApi.deleteBook(id).isSuccessful
//}
}