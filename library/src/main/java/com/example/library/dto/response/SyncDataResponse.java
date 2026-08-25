package com.example.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
public class SyncDataResponse {

    private List<BookSyncResponse> books;

    private List<CategorySyncResponse> categories;

    private List<MemberSyncResponse> members;

    private List<LoanSlipSyncResponse> loanSlips;

    private LocalDateTime timeSync;
}