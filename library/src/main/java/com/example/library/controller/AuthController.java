package com.example.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.request.LoginRequest;
import com.example.library.dto.request.RegisterRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.LoginResponse;
import com.example.library.service.auth.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest request) {
        System.out.println("===== LOGIN CONTROLLER =====");
        int x = 10;
        int y = 5; 
        int z = 30;

        return authService.login(request);
    }
//
//    @PostMapping("/logout")
//    public ApiResponse<Void> logout() {
//
//        return authService.logout();
//    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @RequestBody RegisterRequest request
    ) {

        ApiResponse<Void> response = authService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}