package com.example.myapplication.core.util

import retrofit2.Response

    fun <T> Response<T>.requireBody(): T {

        if (!isSuccessful) {
            throw Exception(message())
        }

        return body()
            ?: throw Exception("Response body is null")
    }
