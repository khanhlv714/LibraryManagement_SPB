package com.example.library.service.loanslip;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.LoanSlipRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.LoanSlipResponse;
import com.example.library.entity.Account;
import com.example.library.entity.Book;
import com.example.library.entity.LoanSlip;
import com.example.library.entity.Member;
import com.example.library.repository.AccountRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LoanSlipRepository;
import com.example.library.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanSlipServiceImpl implements LoanSlipService {

	private final LoanSlipRepository loanSlipRepository;
	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final AccountRepository accountRepository;

	@Override
	public ApiResponse<Void> create(LoanSlipRequest request) {

		if (loanSlipRepository.existsByReceiptNumber(request.getReceiptNumber())) {
			throw new RuntimeException("Receipt number already exists");
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		Account account = accountRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Account not found"));

		Book book = bookRepository.findById(request.getBookId())
				.orElseThrow(() -> new RuntimeException("Book not found"));

		Member member = memberRepository.findById(request.getMemberId())
				.orElseThrow(() -> new RuntimeException("Member not found"));

		if (request.getState() == 0 && loanSlipRepository.existsByBookIdAndStates(book.getId(), 0)) {

			throw new RuntimeException("Book is already borrowed");
		}

		LoanSlip loanSlip = new LoanSlip();

		loanSlip.setReceiptNumber(request.getReceiptNumber());
		loanSlip.setAccount(account);
		loanSlip.setBook(book);
		loanSlip.setMember(member);
		loanSlip.setStates(request.getState());
		loanSlip.setBorrowDate(request.getBorrowDate());
		loanSlip.setDueDate(request.getDueDate());

		loanSlipRepository.save(loanSlip);

		return ApiResponse.success();
	}

	@Override
	public ApiResponse<Void> update(Integer id, LoanSlipRequest request) {

		LoanSlip loanSlip = loanSlipRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan slip not found"));

		if (!loanSlip.getReceiptNumber().equals(request.getReceiptNumber())
				&& loanSlipRepository.existsByReceiptNumber(request.getReceiptNumber())) {

			throw new RuntimeException("Receipt number already exists");
		}

		Book book = bookRepository.findById(request.getBookId())
				.orElseThrow(() -> new RuntimeException("Book not found"));

		Member member = memberRepository.findById(request.getMemberId())
				.orElseThrow(() -> new RuntimeException("Member not found"));

		if (!loanSlip.getBook().getId().equals(book.getId()) && request.getState() == 0
				&& loanSlipRepository.existsByBookIdAndStates(book.getId(), 0)) {

			throw new RuntimeException("Book is already borrowed");
		}

		loanSlip.setReceiptNumber(request.getReceiptNumber());
		loanSlip.setBook(book);
		loanSlip.setMember(member);
		loanSlip.setStates(request.getState());
		loanSlip.setBorrowDate(request.getBorrowDate());
		loanSlip.setDueDate(request.getDueDate());

		loanSlipRepository.save(loanSlip);

		return ApiResponse.success();
	}

	@Override
	public ApiResponse<Void> delete(Integer id) {

		LoanSlip loanSlip = loanSlipRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan slip not found"));

		loanSlipRepository.delete(loanSlip);

		return ApiResponse.success();
	}

	@Override
	public ApiResponse<LoanSlipResponse> getById(Integer id) {

		LoanSlip loanSlip = loanSlipRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan slip not found"));

		LoanSlipResponse response = mapToResponse(loanSlip);

		return ApiResponse.success(response);
	}

	@Override
	public ApiResponse<List<LoanSlipResponse>> getByLibrarianId() {
		
		Authentication authentication =
		        SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Account account = accountRepository.getByUsername(userName);

		List<LoanSlip> result = loanSlipRepository.findByAccountId(account.getId())
				.orElseThrow(() -> new RuntimeException("Loan slip not found"));

		List<LoanSlipResponse> responses = result.stream().map(this::mapToResponse).toList();

		return ApiResponse.success(responses);
	}

	@Override
	public ApiResponse<List<LoanSlipResponse>> getAll() {

		List<LoanSlipResponse> responses = loanSlipRepository.findAll().stream().map(this::mapToResponse).toList();

		return ApiResponse.success(responses);
	}

	private LoanSlipResponse mapToResponse(LoanSlip loanSlip) {

		LoanSlipResponse response = new LoanSlipResponse();

		response.setId(loanSlip.getId());
		response.setReceiptNumber(loanSlip.getReceiptNumber());

		response.setCreatedByAccountId(loanSlip.getAccount().getId());
		response.setCreatedByUsername(loanSlip.getAccount().getUsername());

		response.setBookId(loanSlip.getBook().getId());

		response.setBookName(loanSlip.getBook().getBookName());

		response.setBookCode(loanSlip.getBook().getBookCode());

		response.setMemberId(loanSlip.getMember().getId());

		response.setMemberCardNumber(loanSlip.getMember().getCardNumber());

		response.setMemberName(loanSlip.getMember().getName());

		response.setState(loanSlip.getStates());

		response.setBorrowDate(loanSlip.getBorrowDate());

		response.setDueDate(loanSlip.getDueDate());

		return response;
	}

}