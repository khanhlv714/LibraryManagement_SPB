package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.datastore.Session
import com.example.myapplication.core.datastore.SessionManager
import com.example.myapplication.core.datastore.TokenManager
import com.example.myapplication.core.network.ApiResponseHandler
import com.example.myapplication.core.network.handleNetworkException
import com.example.myapplication.data.mapper.AuthMapper.toLoginResult
import com.example.myapplication.data.mapper.AuthMapper.toSession
import com.example.myapplication.data.remote.api.AuthApi
import com.example.myapplication.data.remote.dto.request.LoginRequest
import com.example.myapplication.data.remote.dto.response.ApiErrorResponse
import com.example.myapplication.domain.model.LoginResult
import com.example.myapplication.domain.repository.AuthRepository
import com.google.gson.Gson
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

    private val authApi: AuthApi,

    private val tokenManager: TokenManager,

    private val sessionManager: SessionManager

) : AuthRepository {

    override suspend fun login(
        username: String, password: String
    ): Resource<LoginResult> {

        return try {

            val response = authApi.login(
                LoginRequest(
                    username = username, password = password
                )
            )
            val result = ApiResponseHandler.handle(response)
            when (result) {
                is Resource.Error -> return result
                is Resource.Success -> {
                    val data = result.data
                    tokenManager.saveAccessToken(result.data.accessToken);
                    tokenManager.saveRefreshToken(result.data.refreshToken);
                    sessionManager.saveSession(Session(result.data.username, result.data.role));
                    Resource.Success(data.toLoginResult())
                }
            }


        } catch (e: Exception) {
            handleNetworkException(e)
        }
    }

    override suspend fun logout() {

        tokenManager.clear()

        sessionManager.clear()

    }

    override suspend fun isLoggedIn(): Boolean {

        return tokenManager.getAccessToken() != null

    }
}