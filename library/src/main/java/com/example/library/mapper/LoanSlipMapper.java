package com.example.library.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.library.dto.request.LoanSlipRequest;
import com.example.library.dto.response.LoanSlipResponse;
import com.example.library.dto.response.LoanSlipSyncResponse;
import com.example.library.entity.Account;
import com.example.library.entity.Book;
import com.example.library.entity.LoanSlip;
import com.example.library.entity.Member;

public class LoanSlipMapper{

    public static LoanSlipSyncResponse toSyncResponse(LoanSlip loanSlip) {
        return new LoanSlipSyncResponse(
                loanSlip.getId(),
                loanSlip.getReceiptNumber(),
                loanSlip.getAccount().getId(),
                loanSlip.getBook().getId(),
                loanSlip.getMember().getId(),
                loanSlip.getStates(),
                loanSlip.getVersion(),
                loanSlip.getBorrowDate(),
                loanSlip.getDueDate(),
                loanSlip.getUpdatedAt(),
                loanSlip.getDeleteAt()
        );
    }
    
    public static LoanSlipResponse toLoanSlipResponse(LoanSlip loanSlip){
        return new LoanSlipResponse(
                loanSlip.getId(),
                loanSlip.getReceiptNumber(),
                loanSlip.getAccount().getId(),
                loanSlip.getAccount().getUsername(),
                loanSlip.getBook().getId(),
                loanSlip.getBook().getBookName(),
                loanSlip.getBook().getBookCode(),
                loanSlip.getMember().getId(),
                loanSlip.getMember().getName(),
                loanSlip.getMember().getCardNumber(),
                loanSlip.getStates(),
                loanSlip.getBorrowDate(),
                loanSlip.getDueDate(),
                loanSlip.getUpdatedAt(),
                loanSlip.getDeleteAt()
        );  
    }
    
    public static LoanSlip toLoanSlip(LoanSlipRequest request,Account account,Member member,Book book){
    	LoanSlip loanSlip = new LoanSlip();

		loanSlip.setReceiptNumber(request.getReceiptNumber());
		loanSlip.setAccount(account);
		loanSlip.setBook(book);
		loanSlip.setMember(member);
		loanSlip.setStates(request.getState());
		loanSlip.setBorrowDate(request.getBorrowDate());
		loanSlip.setUpdatedAt(LocalDateTime.now());
		
		loanSlip.setDueDate(request.getDueDate());
		return loanSlip;
     
    }
    
    
}