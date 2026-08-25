package com.example.myapplication.data.mapper

import com.example.myapplication.core.datastore.Session
import com.example.myapplication.data.remote.dto.response.LoginResponse
import com.example.myapplication.domain.model.LoginResult

object AuthMapper {

    fun LoginResponse.toSession(): Session {
        return Session(
            username = username,
            role = role,
        )
    }

    fun LoginResponse.toLoginResult(): LoginResult {
        return LoginResult(
            username = username,
            role = role
        )
    }
}