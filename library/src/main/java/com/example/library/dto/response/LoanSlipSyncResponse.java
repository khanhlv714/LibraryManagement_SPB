package com.example.library.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanSlipSyncResponse {

	   private Integer id;

	    private String receiptNumber;
	    
	    private Integer accountId;
	    
	    private Integer bookId;
	    
	    private Integer memberId;
	        
	    private Integer state;
	    
	    private Long version;

	    private LocalDate borrowDate;
	    
	    private LocalDate dueDate;
	    
	    private LocalDateTime updatedAt;
	    
	    private LocalDateTime deleteAt;
}
