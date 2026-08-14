package com.example.library.service.librarian;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.ChangePasswordRequest;
import com.example.library.dto.request.LibrarianCreateRequest;
import com.example.library.dto.request.LibrarianUpdateRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.LibrarianResponse;
import com.example.library.entity.Account;
import com.example.library.exception.StaffCodeAlreadyExistsException;
import com.example.library.exception.UsernameAlreadyExistsException;
import com.example.library.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {

	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public ApiResponse<Void> create(LibrarianCreateRequest request) {

		if (accountRepository.existsByUsername(request.getUsername())) {
			throw new UsernameAlreadyExistsException("Username already exists");
		}

		if (accountRepository.existsByStaffCode(request.getStaffCode())) {
			throw new StaffCodeAlreadyExistsException("Staff code already exists");
		}

		Account account = Account.builder().username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword())).fullName(request.getFullName())
				.role("LIBRARIAN").staffCode(request.getStaffCode()).build();

		accountRepository.save(account);

		return new ApiResponse<>(true, "Create librarian success", null);
	}

	@Override
	public ApiResponse<Void> update(Integer id, LibrarianUpdateRequest request) {

		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Librarian not found"));
		if (!account.getRole().equals("LIBRARIAN")) {
		    throw new RuntimeException("Librarian not found");
		}

		if (!account.getStaffCode().equals(request.getStaffCode())) {
		    if (accountRepository.existsByStaffCode(request.getStaffCode())) {
		        throw new StaffCodeAlreadyExistsException("Staff code already exists");
		    }

		    account.setStaffCode(request.getStaffCode());
		}

		account.setFullName(request.getFullName());

		accountRepository.save(account);

		return new ApiResponse<>(true, "Update librarian success", null);
	}

	@Override
	public ApiResponse<Void> delete(Integer id) {

		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Librarian not found"));
		if(account.getRole().equals("ADMIN")) {
			throw new RuntimeException("cannot delete admin");
		}

		accountRepository.delete(account);
		return new ApiResponse<>(true, "Delete librarian success", null);
	}

	@Override
	public ApiResponse<Void> changePassword(Integer id, ChangePasswordRequest request) {

		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Librarian not found"));

	    if (!passwordEncoder.matches(
	            request.getOldPassword(),
	            account.getPassword())) {

	        throw new RuntimeException("Old password is incorrect");
	    }

		account.setPassword(passwordEncoder.encode(request.getNewPassword()));

		accountRepository.save(account);

		return new ApiResponse<>(true, "Change password success", null);
	}

	@Override
	public ApiResponse<LibrarianResponse> getById(Integer id) {

		Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Librarian not found"));

		if (!account.getRole().equals("LIBRARIAN")) {
		    throw new RuntimeException("Librarian not found");
		}

		LibrarianResponse response = LibrarianResponse.builder().id(account.getId()).username(account.getUsername())
				.fullName(account.getFullName()).role(account.getRole()).staffCode(account.getStaffCode()).build();

		return new ApiResponse<>(true, "Get librarian success", response);
	}

	@Override
	public ApiResponse<List<LibrarianResponse>> getAll() {

		List<LibrarianResponse> response = accountRepository.findByRole("LIBRARIAN").stream()
				.map(account -> LibrarianResponse.builder().id(account.getId()).username(account.getUsername())
						.fullName(account.getFullName()).role(account.getRole()).staffCode(account.getStaffCode())
						.build())
				.collect(Collectors.toList());

		return new ApiResponse<>(true, "Get librarians success", response);
	}
}