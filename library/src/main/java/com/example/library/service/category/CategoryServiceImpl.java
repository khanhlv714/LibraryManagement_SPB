package com.example.library.service.category;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.CategoryRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.BookResponse;
import com.example.library.dto.response.CategoryResponse;
import com.example.library.entity.Account;
import com.example.library.entity.Category;
import com.example.library.repository.AccountRepository;
import com.example.library.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final AccountRepository accountRepository;

    @Override
    public ApiResponse<Void> create(CategoryRequest request) {

        if (categoryRepository.existsByCategoryCode(request.getCategoryCode())) {
            return new ApiResponse<>(false, "Category code already exists", null);
        }

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(username));

        Category category = Category.builder()
                .categoryCode(request.getCategoryCode())
                .categoryName(request.getCategoryName())
                .createdBy(account)
                .build();

        categoryRepository.save(category);

        return new ApiResponse<>(true, "Create category success", null);
    }
    @Override
    public ApiResponse<List<CategoryResponse>> getAll() {

        List<CategoryResponse> response = categoryRepository.findAllWithBookCount();
               
        return new ApiResponse<>(true, "Get all category success", response);
    }
    @Override
    public ApiResponse<CategoryResponse> getById(Integer id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        CategoryResponse response = CategoryResponse.builder()
                .id(category.getId())
                .categoryCode(category.getCategoryCode())
                .categoryName(category.getCategoryName())
                .createdBy(category.getCreatedBy())
                .build();

        return new ApiResponse<>(true, "Get category success", response);
    }

    @Override
    public ApiResponse<Void> update(
            Integer id,
            CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        category.setCategoryCode(request.getCategoryCode());
        category.setCategoryName(request.getCategoryName());

        categoryRepository.save(category);

        return new ApiResponse<>(true, "Update category success", null);
    }

    @Override
    public ApiResponse<Void> delete(Integer id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        categoryRepository.delete(category);

        return new ApiResponse<>(true, "Delete category success", null);
    }
    
//    public CategoryResponse toResponse(Category category){
//
//        return CategoryResponse.builder()
//                .id(category.getId())
//                .categoryName(category.getCategoryName())
//                .categoryCode(category.getCategoryCode())
//                .createdBy(category.getCreatedBy())
//                .amountBook()
//    }


}