package com.example.library.mapper;

import java.time.LocalDateTime;

import com.example.library.dto.request.BookRequest;
import com.example.library.dto.request.CategoryRequest;
import com.example.library.dto.response.BookResponse;
import com.example.library.dto.response.BookSyncResponse;
import com.example.library.entity.Account;
import com.example.library.entity.Book;
import com.example.library.entity.Category;

public class BookMapper {

    public static BookSyncResponse toSyncResponse(Book book) {
        BookSyncResponse response = new BookSyncResponse();

        response.setId(book.getId());
        response.setBookCode(book.getBookCode());
        response.setBookName(book.getBookName());
        
        response.setPrice(book.getPrice());
        response.setCreatedBy(book.getCreatedBy().getId());
        response.setCategoryId(book.getCategory().getId());

        response.setUpdatedAt(book.getUpdatedAt());
        response.setDeleteAt(book.getDeleteAt());
        response.setVersion(book.getVersion());

        return response;
    }
    

    public static BookResponse toBookResponse(Book book) {

        return BookResponse.builder()
                .id(book.getId())
                .bookCode(book.getBookCode())
                .bookName(book.getBookName())
                .price(book.getPrice())
                .categoryName(book.getCategory().getCategoryName())
                .createdBy(book.getCreatedBy().getUsername())
                .categoryId(book.getCategory().getId())
                .updatedAt(book.getUpdatedAt())
                .deleteAt(book.getDeleteAt())
                .build();
    }
   
    public static Book toBook(BookRequest request, Account account,Category category ) {

        return Book.builder()
                .bookCode(request.getBookCode())
                .bookName(request.getBookName())
                .price(request.getPrice())
                .version(0L)  
                .updatedAt(LocalDateTime.now())
                .deleteAt(null)
                .category(category)
                .createdBy(account)
                .build();
    }
}