package com.example.myapplication.data.remote.dto.response

data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String?
)