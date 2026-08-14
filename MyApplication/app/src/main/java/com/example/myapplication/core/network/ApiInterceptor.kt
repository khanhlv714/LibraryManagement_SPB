package com.example.myapplication.core.network

import com.example.myapplication.core.datastore.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor(val tokenManager: TokenManager) : Interceptor
{

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = tokenManager.getAccessToken()
        val request = chain.request()
            .newBuilder()
            .apply {
                if (!accessToken.isNullOrBlank()) {
                    addHeader(
                        "Authorization",
                        "Bearer $accessToken"
                    )
                }
            }
            .build()
        return chain.proceed(request)
    }

}