package com.example.library.dto.response;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSyncResponse {

    private Integer id;

    private String bookCode;
    
    private String bookName;  ////

    private Integer price;

    private Integer createdBy;
        
    private Integer categoryId;  ////
    
    private Long version;
    
    private LocalDateTime updatedAt;
    
    private LocalDateTime deleteAt;

}