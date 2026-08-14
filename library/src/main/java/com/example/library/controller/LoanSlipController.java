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

import com.example.library.dto.request.LoanSlipRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.LoanSlipResponse;
import com.example.library.service.loanslip.LoanSlipService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/loan-slips")
@RequiredArgsConstructor
public class LoanSlipController {

    private final LoanSlipService loanSlipService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@Valid @RequestBody LoanSlipRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(loanSlipService.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> update(
            @PathVariable Integer id,
            @Valid @RequestBody LoanSlipRequest request) {

        return loanSlipService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Integer id) {

        return loanSlipService.delete(id);
    }

    @GetMapping("/{id}")
    public ApiResponse<LoanSlipResponse> getById(
            @PathVariable Integer id) {

        return loanSlipService.getById(id);
    }
    
    @GetMapping("/librarian")
    public ApiResponse<List<LoanSlipResponse>> getByLibrarianId() {

        return loanSlipService.getByLibrarianId();
    }

    @GetMapping
    public ApiResponse<List<LoanSlipResponse>> getAll() {

        return loanSlipService.getAll();
    }

}