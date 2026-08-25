package com.example.library.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.SyncDataResponse;
import com.example.library.service.SyncService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
public class SyncController {

    private final SyncService syncService;

    @GetMapping("/init-data")
    public ApiResponse<SyncDataResponse> initData() {
         com.example.library.dto.response.ApiResponse data = syncService.initData();
         return data;
    }

    @GetMapping("/since/{time}")
    public ApiResponse<SyncDataResponse> syncSince(
            @PathVariable LocalDateTime time
    ) {
        return syncService.syncSince(time);
    }
}