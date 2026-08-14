package com.example.library.dto.response;

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
}