package com.example.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.request.BookRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.BookResponse;
import com.example.library.service.book.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @RequestBody BookRequest request) {
    	ApiResponse<Void> response = bookService.create(request);
          return ResponseEntity
                  .status(HttpStatus.CREATED)
                  .body(response);
    }

    @GetMapping
    public ApiResponse<List<BookResponse>> getAll() {

        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse<BookResponse> getById(
            @PathVariable Integer id) {

        return bookService.getById(id);
    }

//    @PutMapping("/{id}")
//    public ApiResponse<Void> update(
//            @PathVariable Integer id,
//            @RequestBody BookRequest request) {
//
//        return bookService.update(id, request);
//    }
//
//    @DeleteMapping("/{id}")
//    public ApiResponse<Void> delete(
//            @PathVariable Integer id) {
//
//        return bookService.delete(id);
//    }
}