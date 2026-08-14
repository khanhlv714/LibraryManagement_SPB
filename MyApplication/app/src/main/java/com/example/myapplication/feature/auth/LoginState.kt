package com.example.myapplication.feature.auth
import com.example.myapplication.data.remote.dto.response.LoginResponse

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean? = null,
    val error: String? = null  ,
    val role : String ? = null
)