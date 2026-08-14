package com.example.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	// AuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
    		UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {


        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,DaoAuthenticationProvider authenticationProvider)
            throws Exception {
    	// bean FilterChainProxy luc cau hinh @enablewebsecirity use SecurityFilterChain bean
    	// ServletContainerInitializer
    	// springSecurityFilterChain
    	// ApplicationContextFacade
    	// SpringServletContainerInitializer
    	// FilterChainProxy
    	// DelegatingFilterProxyRegistrationBean
    	// DelegatingFilterProxyRegistrationBean
    	//ServletWebServerApplicationContext
    	// FilterRegistrationBean
    	// DelegatingFilterProxy
    	//DelegatingFilterProxyRegistrationBean
    	// AuthorizationFilter
    	// FlywayAutoConfiguration
    	    http
    	            .csrf(csrf -> csrf.disable())

    	            .sessionManagement(session ->
    	                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

    	            .addFilterBefore(
    	                    jwtAuthenticationFilter,
    	                    UsernamePasswordAuthenticationFilter.class
    	            )

    	            .authorizeHttpRequests(auth -> auth

    	                    // Public
    	                    .requestMatchers(
    	                            "/api/auth/login",
    	                            "/api/auth/register"
    	                    ).permitAll()

    	                    // ADMIN
    	                    .requestMatchers(
    	                            HttpMethod.POST, "/api/books/**"
    	                    ).hasRole("ADMIN")
    	                    .requestMatchers(
    	                            HttpMethod.PUT, "/api/books/**"
    	                    ).hasRole("ADMIN")
    	                    .requestMatchers(
    	                            HttpMethod.DELETE, "/api/books/**"
    	                    ).hasRole("ADMIN")

    	                    .requestMatchers(
    	                            HttpMethod.POST, "/api/categories/**"
    	                    ).hasRole("ADMIN")
    	                    .requestMatchers(
    	                            HttpMethod.PUT, "/api/categories/**"
    	                    ).hasRole("ADMIN")
    	                    .requestMatchers(
    	                            HttpMethod.DELETE, "/api/categories/**"
    	                    ).hasRole("ADMIN")

    	                    // ADMIN + LIBRARIAN được xem
    	                    .requestMatchers(
    	                            HttpMethod.GET, "/api/books/**"
    	                    ).hasAnyRole("ADMIN", "LIBRARIAN")

    	                    .requestMatchers(
    	                            HttpMethod.GET, "/api/categories/**"
    	                    ).hasAnyRole("ADMIN", "LIBRARIAN")

    	                    // LIBRARIAN
    	                    .requestMatchers("/api/members/**")
    	                    .hasRole("LIBRARIAN")

    	                    .requestMatchers("/api/loan-slips/**")
    	                    .hasRole("LIBRARIAN")

    	                    // Các request còn lại
    	                    .anyRequest().authenticated()
    	            )

    	            .authenticationProvider(authenticationProvider);

    	    return http.build();
    }
}