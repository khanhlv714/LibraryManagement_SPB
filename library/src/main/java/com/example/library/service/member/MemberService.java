package com.example.library.service.member;

import java.util.List;

import com.example.library.dto.request.MemberRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.MemberResponse;

public interface MemberService {

    ApiResponse<Void> create(MemberRequest request);

    ApiResponse<Void> update(Integer id, MemberRequest request);

    ApiResponse<Void> delete(Integer id);

    ApiResponse<MemberResponse> getById(Integer id);

    ApiResponse<List<MemberResponse>> getAll();

}