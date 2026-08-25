package com.example.library.dto.response;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberSyncResponse {

    private Integer id;

    private String cardNumber;

    private String name;

    private Integer createdBy;
    
    private Long version;
    
    private LocalDateTime updatedAt;
    
    private LocalDateTime deleteAt; 

}

