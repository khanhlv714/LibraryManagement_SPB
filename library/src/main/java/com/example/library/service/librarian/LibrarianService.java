package com.example.library.service.librarian;

import java.util.List;

import com.example.library.dto.request.ChangePasswordRequest;
import com.example.library.dto.request.LibrarianCreateRequest;
import com.example.library.dto.request.LibrarianUpdateRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.LibrarianResponse;

public interface LibrarianService {

    ApiResponse<Void> create(LibrarianCreateRequest request);

    ApiResponse<Void> update(
            Integer id,
            LibrarianUpdateRequest request);

    ApiResponse<Void> delete(Integer id);

    ApiResponse<Void> changePassword(
            Integer id,
            ChangePasswordRequest request);

    ApiResponse<LibrarianResponse> getById(
            Integer id);

    ApiResponse<List<LibrarianResponse>> getAll();
}