package com.example.library.mapper;

import java.time.LocalDateTime;

import com.example.library.dto.request.MemberRequest;
import com.example.library.dto.response.MemberResponse;
import com.example.library.dto.response.MemberSyncResponse;
import com.example.library.entity.Account;
import com.example.library.entity.Member;

public class MemberMapper {

    public static MemberSyncResponse toSyncResponse(Member member) {
        return new MemberSyncResponse(
                member.getId(),
                member.getCardNumber(),
                member.getName(),
                member.getCreatedBy().getId(),
                member.getVersion(),
                member.getUpdatedAt(),
                member.getDeleteAt()
        );
    }

    public static MemberResponse toMemberResponse(Member member) {

        MemberResponse response = new MemberResponse();

        response.setId(member.getId());
        response.setCardNumber(member.getCardNumber());
        response.setName(member.getName());

        response.setCreatedById(member.getCreatedBy().getId());
        response.setCreatedByUsername(member.getCreatedBy().getUsername());
        
        response.setUpdatedAt(member.getUpdatedAt());
        response.setDeleteAt(member.getDeleteAt());

        return response;

    }
    public static Member toMember(MemberRequest request,Account account) {
    	
        Member member = new Member();
        member.setCardNumber(request.getCardNumber());
        member.setName(request.getName());
        member.setVersion(0L);
        member.setUpdatedAt(LocalDateTime.now());
        member.setDeleteAt(null);
        member.setCreatedBy(account);
        return member;

    }
}