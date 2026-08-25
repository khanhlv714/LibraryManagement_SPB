package com.example.myapplication.core.network

import android.util.Log
import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.datastore.SessionManager
import com.example.myapplication.core.datastore.TokenManager
import com.example.myapplication.data.remote.api.AuthApi
import com.example.myapplication.data.remote.dto.request.RefreshTokenRequest
import com.example.myapplication.data.remote.dto.response.RefreshTokenResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : Authenticator {

    override fun authenticate(
        route: Route?, response: Response
    ): Request? {
        val refreshToken = tokenManager.getRefreshToken() ?: return null
        var resource: Resource<RefreshTokenResponse>? = null;
        runBlocking {
            try {
                val res = authApi.refreshToken(
                    RefreshTokenRequest(refreshToken)
                )
                resource = ApiResponseHandler.handle(res);
            } catch (e: Exception) {
                resource = handleNetworkException(e)
            }
        }
        if (resource is Resource.Success){
            val newAccessToken = resource.data.accessToken
            val newRefreshToken = resource.data.refreshToken;

            runBlocking {
                tokenManager.saveAccessToken(newAccessToken)
                if (newRefreshToken != null) {
                    tokenManager.saveRefreshToken(newRefreshToken)
                }
                Log.d("Log", "save token");
            }

            return response.request.newBuilder().header(
                "Authorization", "Bearer $newAccessToken"
            ).build()
        }else{
            runBlocking {
                tokenManager.clear()
                sessionManager.closeSession()
            }
        }
        return null;
    }
}