package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.dto.request.BookRequest
import com.example.myapplication.data.remote.dto.response.ApiResponse
import com.example.myapplication.data.remote.dto.response.BookResponse
import retrofit2.Response
import retrofit2.http.*

interface BookApi {

    @GET("api/books")
    suspend fun getBooks(): Response<ApiResponse<List<BookResponse>>>

    @GET("api/books/{id}")
    suspend fun getBookById(
        @Path("id") id: Int
    ): Response<BookResponse>

    @POST("books")
    suspend fun addBook(
        @Body book: BookRequest
    ): Response<BookResponse>

    @PUT("books/{id}")
    suspend fun updateBook(
        @Path("id") id: Int,
        @Body book: BookRequest
    ): Response<BookResponse>

    @DELETE("books/{id}")
    suspend fun deleteBook(
        @Path("id") id: Int
    ): Response<Unit>
}