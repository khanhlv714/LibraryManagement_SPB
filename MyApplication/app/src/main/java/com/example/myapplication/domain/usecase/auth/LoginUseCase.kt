package com.example.myapplication.domain.usecase.auth

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.LoginResult
import com.example.myapplication.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        username: String,
        password: String
    ): Resource<LoginResult> {

        if (username.isBlank()) {
            return Resource.Error("Username is required")
        }

        if (password.isBlank()) {
            return Resource.Error("Password is required")
        }

        return repository.login(username, password)
    }
}