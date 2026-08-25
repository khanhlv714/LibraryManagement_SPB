package com.example.library.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.SyncDataResponse;
import com.example.library.mapper.BookMapper;
import com.example.library.mapper.CategoryMapper;
import com.example.library.mapper.LoanSlipMapper;
import com.example.library.mapper.MemberMapper;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import com.example.library.repository.LoanSlipRepository;
import com.example.library.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SyncService {

	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;
	private final MemberRepository memberRepository;
	private final LoanSlipRepository loanSlipRepository;

	public ApiResponse<SyncDataResponse> initData() {
		
		try {
			LocalDateTime time = LocalDateTime.now();
			SyncDataResponse data = new SyncDataResponse(
			        bookRepository.findAll()
			                .stream()
			                .map(BookMapper::toSyncResponse)
			                .toList(),

			        categoryRepository.findAll()
			                .stream()
			                .map(CategoryMapper::toSyncResponse)
			                .toList(),

			        memberRepository.findAll()
			                .stream()
			                .map(MemberMapper::toSyncResponse)
			                .toList(),

			        loanSlipRepository.findAll()
			                .stream()
			                .map(LoanSlipMapper::toSyncResponse)
			                .toList(),

			        time
			);
			return ApiResponse.success(data);
		} catch (Exception e) {
			throw new RuntimeException("Sync data fail - init data");
		}

	}

	public ApiResponse<SyncDataResponse> syncSince(LocalDateTime time) {
		try {
			LocalDateTime newTime = LocalDateTime.now();
			SyncDataResponse data = new SyncDataResponse(
			        bookRepository.findByUpdatedAtGreaterThanEqual(time)
			                .stream()
			                .map(BookMapper::toSyncResponse)
			                .toList(),

			        categoryRepository.findByUpdatedAtGreaterThanEqual(time)
			                .stream()
			                .map(CategoryMapper::toSyncResponse)
			                .toList(),

			        memberRepository.findByUpdatedAtGreaterThanEqual(time)
			                .stream()
			                .map(MemberMapper::toSyncResponse)
			                .toList(),

			        loanSlipRepository.findByUpdatedAtGreaterThanEqual(time)
			                .stream()
			                .map(LoanSlipMapper::toSyncResponse)
			                .toList(),

			                newTime
			);

			return ApiResponse.success(data);
		} catch (Exception e) {
			throw new RuntimeException("Sync data fail - update data");
		}

	}
}