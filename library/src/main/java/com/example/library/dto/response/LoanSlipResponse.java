package com.example.library.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanSlipResponse {
	
    private Integer id;

    private String receiptNumber;
    
    private Integer createdByAccountId;
    
    private String createdByUsername;

    private Integer bookId;
    
    private String bookName;
    
    private String bookCode;

    private Integer memberId;
    
    private String memberName;
    
    private String memberCardNumber;

    private Integer state;

    private LocalDate borrowDate;
    
    private LocalDate dueDate;
    
    private LocalDateTime updatedAt;
    
    private LocalDateTime deleteAt;  
}