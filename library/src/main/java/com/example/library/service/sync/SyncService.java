package com.example.library.service.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.example.library.dto.response.SyncInfoResponse;
import com.example.library.entity.*;
import com.example.library.enums.SyncType;
import com.example.library.repository.*;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.SyncDataResponse;
import com.example.library.core.SyncConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SyncService {

	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;
	private final MemberRepository memberRepository;
	private final LoanSlipRepository loanSlipRepository;
	private final SyncRepository syncRepository;
	private final AccountRepository accountRepository;

	public ApiResponse<SyncInfoResponse> initInfo() {
		
		try {
			long snapshot = syncRepository.getCurrentSyncVersion();
			return ApiResponse.success(new SyncInfoResponse(snapshot,SyncConstants.DEFAULT_SYNC_LIMIT));
		} catch (Exception e) {
			throw new RuntimeException("Sync data fail - init data");
		}

	}

	public ApiResponse<?> initDataPage(
			long cursor,
			int limit,
			long snapshotVersion,
			SyncType syncType
	) {
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();

		Account account = accountRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> new RuntimeException("Account not found"));

		return switch (syncType) {

			case BOOKS -> {
				List<Book> result = bookRepository.initBooks(
						cursor,
						snapshotVersion,
						Pageable.ofSize(limit + 1)
				);

				boolean hasNext = result.size() > limit;
				int nextCursor = -1;

				if (hasNext) {
					nextCursor = result.get(limit - 1).getId();
					result = result.subList(0, limit);
				}

				yield ApiResponse.success(
						new SyncDataResponse<>(
								hasNext,
								nextCursor,
								result
						)
				);
			}

			case CATEGORIES -> {
				List<Category> result = categoryRepository.initCategory(
						cursor,
						snapshotVersion,
						Pageable.ofSize(limit + 1)
				);

				boolean hasNext = result.size() > limit;
				int nextCursor = -1;

				if (hasNext) {
					nextCursor = result.get(limit - 1).getId();
					result = result.subList(0, limit);
				}

				yield ApiResponse.success(
						new SyncDataResponse<>(
								hasNext,
								nextCursor,
								result
						)
				);
			}

			case MEMBERS -> {
				List<Member> result = memberRepository.initMembers(
						cursor,
						snapshotVersion,
						Pageable.ofSize(limit + 1),
						account.getId()
				);

				boolean hasNext = result.size() > limit;
				int nextCursor = -1;

				if (hasNext) {
					nextCursor = result.get(limit - 1).getId();
					result = result.subList(0, limit);
				}

				yield ApiResponse.success(
						new SyncDataResponse<>(
								hasNext,
								nextCursor,
								result
						)
				);
			}

			case LOAN_SLIPS -> {
				List<LoanSlip> result = loanSlipRepository.initLoanSlips(
						cursor,
						snapshotVersion,
						Pageable.ofSize(limit + 1)
				);

				boolean hasNext = result.size() > limit;
				int nextCursor = -1;

				if (hasNext) {
					nextCursor = result.get(limit - 1).getId();
					result = result.subList(0, limit);
				}

				yield ApiResponse.success(
						new SyncDataResponse<>(
								hasNext,
								nextCursor,
								result
						)
				);
			}

			case ACCOUNTS -> {
				List<Account> result = accountRepository.initAccounts(
						cursor,
						snapshotVersion,
						Pageable.ofSize(limit + 1)
				);

				boolean hasNext = result.size() > limit;
				int nextCursor = -1;

				if (hasNext) {
					nextCursor = result.get(limit - 1).getId();
					result = result.subList(0, limit);
				}

				yield ApiResponse.success(
						new SyncDataResponse<>(
								hasNext,
								nextCursor,
								result
						)
				);
			}
		};
	}

//	public ApiResponse<SyncDataResponse> syncSince(LocalDateTime time) {
//		try {
//			LocalDateTime newTime = LocalDateTime.now();
//			SyncDataResponse data = new SyncDataResponse(
//			        bookRepository.findByUpdatedAtGreaterThanEqual(time)
//			                .stream()
//			                .map(BookMapper::toSyncResponse)
//			                .toList(),
//
//			        categoryRepository.findByUpdatedAtGreaterThanEqual(time)
//			                .stream()
//			                .map(CategoryMapper::toSyncResponse)
//			                .toList(),
//
//			        memberRepository.findByUpdatedAtGreaterThanEqual(time)
//			                .stream()
//			                .map(MemberMapper::toSyncResponse)
//			                .toList(),
//
//			        loanSlipRepository.findByUpdatedAtGreaterThanEqual(time)
//			                .stream()
//			                .map(LoanSlipMapper::toSyncResponse)
//			                .toList(),
//
//			                newTime
//			);
//
//			return ApiResponse.success(data);
//		} catch (Exception e) {
//			throw new RuntimeException("Sync data fail - update data");
//		}
//
//	}
}