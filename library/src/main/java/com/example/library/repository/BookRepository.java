package com.example.library.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.library.dto.response.CategoryResponse;
import com.example.library.entity.Book;

@Repository
public interface BookRepository
        extends JpaRepository<Book, Integer> {

    boolean existsByBookCode(String bookCode);

    Optional<Book> findByBookCode(String bookCode);
    
    List<Book> findByUpdatedAtGreaterThanEqual(LocalDateTime time);
    
  
}