package com.example.library.controller;

import java.time.LocalDateTime;

import com.example.library.dto.response.SyncInfoResponse;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.example.library.enums.SyncType;
import com.example.library.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.SyncDataResponse;
import com.example.library.service.sync.SyncService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
public class SyncController {

    private final SyncService syncService;

    @GetMapping("/init-info")
    public ApiResponse<SyncInfoResponse> infoInit() {
        return syncService.initInfo();
    }

    // GET http://localhost:8080/api/init/books?cursor=500&limit=500&snapshotVersion=1000
    @GetMapping("/init/{resource}")
    public ApiResponse<SyncDataResponse> initBooks(
            @PathVariable String resource,
            @RequestParam(defaultValue = "0") long cursor,
            @RequestParam(defaultValue = "500") int limit,
            @RequestParam Long snapshotVersion
    ) {

        return syncService.initDataPage(cursor,limit,snapshotVersion,SyncType.from(resource));
    }

    @GetMapping("/since/{time}")
    public ApiResponse<SyncDataResponse> syncSince(
            @PathVariable LocalDateTime time
    ) {
        return syncService.syncSince(time);
    }
}