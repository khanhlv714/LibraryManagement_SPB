package com.example.library.service.auth;

import com.example.library.dto.request.LoginRequest;
import com.example.library.dto.request.RegisterRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.LoginResponse;

public interface AuthService {

	ApiResponse<LoginResponse> login(LoginRequest request);
    ApiResponse<Void> logout();
    ApiResponse<Void> register(RegisterRequest request);

}