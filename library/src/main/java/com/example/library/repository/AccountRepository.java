package com.example.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Account;

@Repository
public interface AccountRepository
        extends JpaRepository<Account,Integer> {

    Optional<Account> findByUsername(String username);

    boolean existsByUsername(String username);

    Account getByUsername(String username);

    
    boolean existsByStaffCode(String staffCode);

    List<Account> findByRole(String role);

}