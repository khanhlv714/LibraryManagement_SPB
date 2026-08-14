package com.example.library.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class BookRequest {

    @NotBlank
    private String bookCode;

    @NotBlank
    private String bookName;

    @NotNull
    @Min(0)
    private Integer price;

    @NotNull
    private Integer categoryId;
}