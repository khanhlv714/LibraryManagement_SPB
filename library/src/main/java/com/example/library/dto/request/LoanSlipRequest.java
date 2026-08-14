package com.example.library.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class LoanSlipRequest {

    @NotBlank(message = "Receipt number is required")
    private String receiptNumber;

    @NotNull(message = "Book is required")
    private Integer bookId;

    @NotNull(message = "Member is required")
    private Integer memberId;

    @NotNull(message = "State is required")
    private Integer state;

    @NotNull(message = "Borrow date is required")
    private LocalDate borrowDate;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

}