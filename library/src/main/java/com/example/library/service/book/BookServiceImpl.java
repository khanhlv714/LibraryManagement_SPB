package com.example.library.service.book;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.library.dto.request.BookRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.BookResponse;
import com.example.library.entity.Account;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.AccountRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final CategoryRepository categoryRepository;

    private final AccountRepository accountRepository;

    @Override
    public ApiResponse<Void> create(BookRequest request) {

        if (bookRepository.existsByBookCode(request.getBookCode())) {
            //return new ApiResponse<>(false, "Book code already exists", null);
        	   throw new RuntimeException(
                       "Book code already exists" //
               );
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        Account account = accountRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Book book = BookMapper.toBook(request, account, category);

        bookRepository.save(book);

        return new ApiResponse<>(true, "Create book success", null);
    }

    @Override
    public ApiResponse<List<BookResponse>> getAll() {

        List<BookResponse> response = bookRepository.findAll()
                .stream()
                .map(BookMapper::toBookResponse)
                .toList();

        return new ApiResponse<>(true, "Get all book success", response);
    }

    @Override
    public ApiResponse<BookResponse> getById(Integer id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return new ApiResponse<>(true, "Get book success", BookMapper.toBookResponse(book));
    }

//    @Override
//    public ApiResponse<Void> update(Integer id, BookRequest request) {
//
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Book not found"));
//
//        if (!book.getBookCode().equals(request.getBookCode())
//                && bookRepository.existsByBookCode(request.getBookCode())) {
//
//            return new ApiResponse<>(false, "Book code already exists", null);
//        }
//
//        Category category = categoryRepository.findById(request.getCategoryId())
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        book.setBookCode(request.getBookCode());
//        book.setBookName(request.getBookName());
//        book.setPrice(request.getPrice());
//        book.setCategory(category);
//        book.setUpdatedAt(LocalDateTime.now());
//
//        bookRepository.save(book);
//
//        return new ApiResponse<>(true, "Update book success", null);
//    }

//    @Override
//    public ApiResponse<Void> delete(Integer id) {
//
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Book not found"));
//
//        LocalDateTime now = LocalDateTime.now();
//
//        book.setDeleteAt(now);
//        book.setUpdatedAt(now);
//
//        bookRepository.save(book);
//
//        return new ApiResponse<>(true, "Delete book success", null);
//    }
}