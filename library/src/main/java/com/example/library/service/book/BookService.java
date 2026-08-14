package com.example.library.service.book;

import java.util.List;

import com.example.library.dto.request.BookRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.BookResponse;

public interface BookService {

    ApiResponse<Void> create(BookRequest request);

    ApiResponse<List<BookResponse>> getAll();

    ApiResponse<BookResponse> getById(Integer id);

    ApiResponse<Void> update(Integer id, BookRequest request);

    ApiResponse<Void> delete(Integer id);
}