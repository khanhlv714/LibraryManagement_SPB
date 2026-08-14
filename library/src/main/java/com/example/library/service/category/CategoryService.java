package com.example.library.service.category;

import java.util.List;

import com.example.library.dto.request.CategoryRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.CategoryResponse;

public interface CategoryService{

    ApiResponse<Void> create(CategoryRequest request);

    ApiResponse<List<CategoryResponse>> getAll();

    ApiResponse<CategoryResponse> getById(Integer id);

    ApiResponse<Void> update(Integer id,CategoryRequest request);

    ApiResponse<Void> delete(Integer id);

}