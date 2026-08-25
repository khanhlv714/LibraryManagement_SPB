package com.example.library.security;

import java.io.IOException;
import java.util.List;

import com.example.library.service.security_service.AccountSecurityService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter
        extends OncePerRequestFilter { // extend filter //


    private final JwtService jwtService;
    private final AccountSecurityService accountSecurityService;



    public JwtAuthenticationFilter(
            JwtService jwtService,
            AccountSecurityService accountSecurityService
    ) {
        this.jwtService = jwtService;
        this.accountSecurityService = accountSecurityService;
    }



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException {

        // Lấy Authorization header
    	// SecurityContextHolderFilter
        String authHeader =
                request.getHeader("Authorization");

        // Không có token thì cho đi tiếp

        if (authHeader == null ||
            !authHeader.startsWith("Bearer ")) {


            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }



        // Cắt "Bearer "

        String token =
                authHeader.substring(7);
        try {
            // Lấy username từ JWT

            String username =
                    jwtService.extractUsername(token);

            // Nếu chưa đăng nhập trong SecurityContext

            if (username != null &&
                SecurityContextHolder
                    .getContext()
                    .getAuthentication() == null){ //  Has an authenticator been added? Perhaps a previous filter already had its value set.

                // Lấy role từ JWT

                String role =
                        jwtService.extractRole(token);

                Long securityVersion = jwtService.extractSecurityVersion(token);
                if(accountSecurityService.isSecurityVersionValid(username,securityVersion) == false){
                    throw new Exception("version security fail");
                }
                var authorities =
                        List.of(
                            new SimpleGrantedAuthority(
                                "ROLE_" + role
                            )
                        );



                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                authorities
                        );



                // Đưa user hiện tại vào SecurityContext

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);

            }

        } catch (Exception e) {
            // Token sai / hết hạn // phien ban security sai
            SecurityContextHolder
                    .clearContext();

        }



        // Cho request chạy tiếp

        filterChain.doFilter(
                request,
                response
        );
    }
}