package com.example.library.service.loanslip;

import java.util.List;

import com.example.library.dto.request.LoanSlipRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.LoanSlipResponse;

public interface LoanSlipService {

    ApiResponse<Void> create(LoanSlipRequest request);

    ApiResponse<Void> update(Integer id, LoanSlipRequest request);

    ApiResponse<Void> delete(Integer id);

    ApiResponse<LoanSlipResponse> getById(Integer id);

    ApiResponse<List<LoanSlipResponse>> getAll();
    
    ApiResponse<List<LoanSlipResponse>> getByLibrarianId();

}