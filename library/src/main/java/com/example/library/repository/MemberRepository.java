package com.example.library.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Book;
import com.example.library.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    boolean existsByCardNumber(String cardNumber);

    Optional<Member> findByCardNumber(String cardNumber);
    
    List<Member> findByUpdatedAtGreaterThanEqual(LocalDateTime time);

}