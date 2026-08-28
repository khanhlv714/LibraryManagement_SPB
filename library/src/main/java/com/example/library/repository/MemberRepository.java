package com.example.library.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.library.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.Book;
import com.example.library.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    boolean existsByCardNumber(String cardNumber);

    Optional<Member> findByCardNumber(String cardNumber);
    
    List<Member> findByUpdatedAtGreaterThanEqual(LocalDateTime time);

    @Query("""
            SELECT m
            FROM Member m
            WHERE m.createdBy.id  = :accountId 
                        And m.version <= :snapshotVersion
              AND m.id > :cursor
            ORDER BY m.id ASC
            """)
    List<Member> initMembers(
            @Param("cursor") long cursor,
            @Param("snapshotVersion") long snapshotVersion,
            Pageable pageable,
            @Param("accountId") int accountId
    );

}