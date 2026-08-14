package com.example.myapplication.core.network

import android.util.Log
import com.example.myapplication.core.common.Resource
import com.example.myapplication.data.remote.dto.response.ApiErrorResponse
import com.example.myapplication.data.remote.dto.response.ApiResponse
import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T> handleNetworkException(e: Exception): Resource<T> {
    Log.d("Error", e.message ?: "no result");
    return when (e) {
        is UnknownHostException -> Resource.Error("No internet connection")
        is SocketTimeoutException -> Resource.Error("Connection timeout")
        is IOException -> Resource.Error("Network error")
        else -> Resource.Error("Application error")
    }
}
