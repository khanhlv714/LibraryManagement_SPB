package com.example.library.service.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.LoginRequest;
import com.example.library.dto.request.RegisterRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.LoginResponse;
import com.example.library.entity.Account;
import com.example.library.exception.StaffCodeAlreadyExistsException;
import com.example.library.exception.UsernameAlreadyExistsException;
import com.example.library.repository.AccountRepository;
import com.example.library.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final PasswordEncoder passwordEncoder;

	private final AccountRepository accountRepository;

	private final AuthenticationManager authenticationManager;

	private final JwtService jwtService;

	@Override
	public ApiResponse<LoginResponse> login(LoginRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		UserDetails user = (UserDetails) authentication.getPrincipal();

		String role = user.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

		String accessToken = jwtService.generateToken(user.getUsername(), role);

		String refreshToken = jwtService.generateRefreshToken(user.getUsername());

		LoginResponse response = LoginResponse.builder().username(user.getUsername()).role(role)
				.accessToken(accessToken).refreshToken(refreshToken).build();

		return new ApiResponse<>(true, "Login success", response);

	}

	@Override
	public ApiResponse<Void> register(RegisterRequest request) {
	    System.out.println("===== REGISTER Start=====");

	    if (accountRepository.existsByUsername(request.getUsername())) {
	    	   throw new UsernameAlreadyExistsException("Username already exists");
	    }
	    if (accountRepository.existsByStaffCode(request.getStaffCode())) {
	    	   throw new StaffCodeAlreadyExistsException("StaffCode already exists");
	    }
	    System.out.println("===== REGISTER check account=====");

	    Account account = Account.builder()
	            .username(request.getUsername())
	            .password(passwordEncoder.encode(request.getPassword()))
	            .fullName(request.getFullName())
	            .role(request.getRole())
	            .staffCode(request.getStaffCode())
	            .build();
	    System.out.println("===== REGISTER save=====");


	    accountRepository.save(account);
	    System.out.println("===== REGISTER complete=====");


	    return new ApiResponse<>(true, "Register success", null);
	}


	@Override
	public ApiResponse<Void> logout() {
		return new ApiResponse<>(true, "logout success", null);
	}
}

//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//
//    private final AdminRepository adminRepository;
//
//    private final LibrarianRepository librarianRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final JwtService jwtService;
//
//
//
//    @Override
//    public ApiResponse<LoginResponse> loginAdmin(
//            LoginRequest request) {
//
//
//        Optional<AdminAccount> optionalAdmin =
//                adminRepository.findByUsername(
//                        request.getUserName()
//                );
//
//
//        if(optionalAdmin.isEmpty()) {
//            return ApiResponse.error(
//                    "Admin username not found"
//            );
//        }
//
//        AdminAccount admin = optionalAdmin.get();
//
//        if(!passwordEncoder.matches(
//                request.getPassword(),
//                admin.getPassword()
//        )) {
//
//            return ApiResponse.error(
//                    "Wrong password"
//            );
//        }
//
//
//        String accessToken =
//                jwtService.generateToken(
//                        admin.getUsername(),
//                        "ADMIN"
//                );
//
//
//        String refreshToken =
//                jwtService.generateRefreshToken(
//                        admin.getUsername()
//                );
//
//
//        LoginResponse response =
//                LoginResponse.builder()
//                        .id(admin.getId())
//                        .username(admin.getUsername())
//                        .role("ADMIN")
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//
//
//        return new ApiResponse<LoginResponse>(true, "login success",response);
//    }
//    @Override
//    public ApiResponse<LoginResponse> loginLibrarian(
//            LoginRequest request) {
//
//
//        Account librarian =
//                librarianRepository
//                        .findByUsername(request.getUserName())
//                        .orElseThrow(() ->
//                                new RuntimeException(
//                                        "Librarian username not found"
//                                )
//                        );
//
//
//        // kiểm tra password
//        if (!passwordEncoder.matches(
//                request.getPassword(),
//                librarian.getPassword()
//        )) {
//
//            throw new RuntimeException(
//                    "Wrong password"
//            );
//        }
//
//
//
//        String accessToken =
//                jwtService.generateToken(
//                        librarian.getUserName(),
//                        "LIBRARIAN"
//                );
//
//
//        String refreshToken =
//                jwtService.generateRefreshToken(
//                        librarian.getUserName()
//                );
//
//
//
//        LoginResponse response =
//                LoginResponse.builder()
//                        .id(librarian.getId())
//                        .username(librarian.getUserName())
//                        .role("LIBRARIAN")
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//
//
//
//        return new ApiResponse<LoginResponse>(true,"login success",response);
//    }
//
//
//
//
//    @Override
//    public ApiResponse<Void> logout() {
//
//        /*
//            JWT thường không cần xử lý logout ở server.
//
//            Client:
//            - xóa accessToken
//            - xóa refreshToken
//
//
//            Nếu cần logout cưỡng chế:
//            - lưu refreshToken vào blacklist
//            - kiểm tra blacklist trong JwtFilter
//        */
//
//        return new ApiResponse<Void>(true,"logout success",null);
//    }
//
//}