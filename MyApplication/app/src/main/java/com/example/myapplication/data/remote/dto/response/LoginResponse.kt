package com.example.myapplication.data.remote.dto.response

data class LoginResponse(
     val username: String,

     val role: String,

     val accessToken: String,

     val refreshToken: String,
)