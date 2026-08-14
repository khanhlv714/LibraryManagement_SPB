package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.network.ApiResponseHandler
import com.example.myapplication.core.network.handleNetworkException
import com.example.myapplication.core.util.requireBody
import com.example.myapplication.data.mapper.bookMapper.toBook
import com.example.myapplication.data.remote.api.BookApi
import com.example.myapplication.data.remote.dto.request.BookRequest
import com.example.myapplication.data.remote.dto.response.ApiErrorResponse
import com.example.myapplication.data.remote.dto.response.BookResponse
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.repository.BookRepository
import com.google.gson.Gson
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
class BookRepositoryImpl @Inject constructor(
    private val bookApi: BookApi
) : BookRepository {

        override suspend fun getBooks(): Resource<List<Book>> {
            return try {
                val res = bookApi.getBooks()
                val result = ApiResponseHandler.handle(res)
                when(result) {
                    is Resource.Success -> {
                        val data = result.data

                        val books = data.map { item ->
                            item.toBook()
                        }
                        return Resource.Success(books)
                    }
                    is Resource.Error -> return result
                }

            }catch(e: Exception) {
                handleNetworkException(e)
            }
        }

    suspend fun getBookById(id: Int): BookResponse {
        return bookApi.getBookById(id).requireBody()
    }

    suspend fun addBook(book: BookRequest): BookResponse {
        return bookApi.addBook(book).requireBody()
    }

//    suspend fun updateBook(book: BookRequest): BookResponse {
//        return bookApi.updateBook(book.id, book).requireBody()
//    }

    suspend fun deleteBook(id: Int): Boolean {
        return bookApi.deleteBook(id).isSuccessful
    }
}