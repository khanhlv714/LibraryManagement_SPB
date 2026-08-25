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
public class BookResponse {

    private Integer id;

    private String bookCode;
    
    private String bookName;

    private Integer price;

    private String categoryName;

    private String createdBy;
        
    private Integer categoryId;
    
    private LocalDateTime updatedAt;
    
    private LocalDateTime deleteAt;

}