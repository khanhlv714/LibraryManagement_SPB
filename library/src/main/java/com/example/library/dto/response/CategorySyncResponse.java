package com.example.library.dto.response;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorySyncResponse {

    private Integer id;

    private String categoryCode;

    private String categoryName;

    private Integer createdBy;
        
    private Long version;

    private LocalDateTime updatedAt;
    
    private LocalDateTime deleteAt;
    
}
