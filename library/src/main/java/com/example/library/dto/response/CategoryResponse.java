package com.example.library.dto.response;

import com.example.library.entity.Account;

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
public class CategoryResponse {

    private Integer id;

    private String categoryCode;

    private String categoryName;

    private Account createdBy;
    
    private Long amountBook;
}