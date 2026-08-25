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

import com.example.library.dto.request.ChangePasswordRequest;
import com.example.library.dto.request.LibrarianCreateRequest;
import com.example.library.dto.request.LibrarianUpdateRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.LibrarianResponse;
import com.example.library.service.librarian.LibrarianService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/librarians")
@RequiredArgsConstructor
public class LibrarianController {

	private final LibrarianService librarianService;

	@PostMapping
	public ResponseEntity<ApiResponse<Void>> create(@Valid @RequestBody LibrarianCreateRequest request) {

		ApiResponse<Void> response = librarianService.create(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

//	@PutMapping("/{id}")
//	public ApiResponse<Void> update(@PathVariable Integer id, @Valid @RequestBody LibrarianUpdateRequest request) {
//
//		return librarianService.update(id, request);
//	}
//
//	@DeleteMapping("/{id}")
//	public ApiResponse<Void> delete(@PathVariable Integer id) {
//
//		return librarianService.delete(id);
//	}
//
//	@PutMapping("/{id}/change-password")
//	public ApiResponse<Void> changePassword(@PathVariable Integer id,
//			@Valid @RequestBody ChangePasswordRequest request) {
//
//		return librarianService.changePassword(id, request);
//	}

//	@GetMapping("/{id}")
//	public ApiResponse<LibrarianResponse> getById(@PathVariable Integer id) {
//
//		return librarianService.getById(id);
//
//	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<LibrarianResponse>>> getAll() {

		ApiResponse<List<LibrarianResponse>> response = librarianService.getAll();

		return ResponseEntity.ok(response);
	}
}