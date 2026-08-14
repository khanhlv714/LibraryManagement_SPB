package com.example.library.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {

    private Integer id;

    private String cardNumber;

    private String name;

    private Integer createdById;

    private String createdByUsername;

}
