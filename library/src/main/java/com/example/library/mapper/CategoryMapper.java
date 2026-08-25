package com.example.library.mapper;

import java.time.LocalDateTime;

import com.example.library.dto.request.CategoryRequest;
import com.example.library.dto.response.CategoryResponse;
import com.example.library.dto.response.CategorySyncResponse;
import com.example.library.entity.Account;
import com.example.library.entity.Category;

public class CategoryMapper {

    public static CategorySyncResponse toSyncResponse(Category category) {
    	
    	return CategorySyncResponse.builder()
         .id(category.getId())
        .categoryCode(category.getCategoryCode())
        .categoryName(category.getCategoryName())
        .createdBy(category.getCreatedBy().getId())
        .version(category.getVersion())
        .updatedAt(category.getUpdatedAt())
        .deleteAt(category.getDeleteAt())
        .build();
    }
    

    public static CategoryResponse toCategoryResponse(Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .categoryCode(category.getCategoryCode())
                .categoryName(category.getCategoryName())
                .categoryName(category.getCategoryName())
                .accountId(category.getCreatedBy().getId())
                .updatedAt(category.getUpdatedAt())
                .deleteAt(category.getDeleteAt())
                .build();
    }
    
    public static Category toCategory(CategoryRequest category, Account createdByAccount) {

        return Category.builder()
                .categoryCode(category.getCategoryCode())
                .categoryName(category.getCategoryName())
                .createdBy(createdByAccount)
                .version(0L)
                .updatedAt(LocalDateTime.now())
                .deleteAt(null)
                .build();
    }
}