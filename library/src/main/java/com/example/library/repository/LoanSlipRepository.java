package com.example.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.entity.LoanSlip;
import com.example.library.entity.Member;

public interface LoanSlipRepository extends JpaRepository<LoanSlip, Integer> {

    boolean existsByReceiptNumber(String receiptNumber);

    Optional<LoanSlip> findByReceiptNumber(String receiptNumber);

    boolean existsByBookIdAndStates(Integer bookId, Integer states);
    
    Optional<List<LoanSlip>> findByAccountId( int accountId);

    

}