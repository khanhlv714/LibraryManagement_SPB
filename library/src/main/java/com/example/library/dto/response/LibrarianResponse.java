package com.example.library.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianResponse {

    private Integer id;

    private String username;

    private String fullName;

    private String role;

    private String staffCode;
    
    private LocalDateTime updatedAt;
    
    private LocalDateTime deleteAt;
}