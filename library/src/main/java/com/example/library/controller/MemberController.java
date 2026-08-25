package com.example.library.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.request.MemberRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.MemberResponse;
import com.example.library.service.member.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> create(@Valid @RequestBody MemberRequest request) {
        return memberService.create(request);
    }

//    @PutMapping("/{id}")
//    public ApiResponse<Void> update(
//            @PathVariable Integer id,
//            @Valid @RequestBody MemberRequest request) {
//
//        return memberService.update(id, request);
//    }
//
//    @DeleteMapping("/{id}")
//    public ApiResponse<Void> delete(@PathVariable Integer id) {
//        return memberService.delete(id);
//    }

    @GetMapping("/{id}")
    public ApiResponse<MemberResponse> getById(@PathVariable Integer id) {
        return memberService.getById(id);
    }

    @GetMapping
    public ApiResponse<List<MemberResponse>> getAll() {
        return memberService.getAll();
    }
}