package com.example.myapplication.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

    private val loginUseCase: LoginUseCase

) : ViewModel() {
    private var loginJob: Job? = null

    private val _loginState = MutableStateFlow(LoginState())

    val loginState: StateFlow<LoginState> =
        _loginState.asStateFlow()

    fun login(
        username: String,
        password: String
    ) {

        loginJob?.cancel()

        loginJob = viewModelScope.launch {

            _loginState.value = LoginState(
                isLoading = true
            )

            when (val result = loginUseCase(username, password)) {

                is Resource.Success -> {

                    _loginState.value = LoginState(
                        role = result.data.role,
                        isSuccess = true
                    )

                }

                is Resource.Error -> {

                    _loginState.value = LoginState(
                        error = result.message
                    )

                }
            }
        }
    }

}