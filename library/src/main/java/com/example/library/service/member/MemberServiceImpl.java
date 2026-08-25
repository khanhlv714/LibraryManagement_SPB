package com.example.library.service.member;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.dto.request.MemberRequest;
import com.example.library.dto.response.ApiResponse;
import com.example.library.dto.response.MemberResponse;
import com.example.library.entity.Account;
import com.example.library.entity.Member;
import com.example.library.mapper.MemberMapper;
import com.example.library.repository.AccountRepository;
import com.example.library.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;

    @Override
    public ApiResponse<Void> create(MemberRequest request) {

        if (memberRepository.existsByCardNumber(request.getCardNumber())) {
            throw new RuntimeException("Card number already exists");
        }

        Account account = accountRepository.findById(request.getCreatedBy())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        memberRepository.save(MemberMapper.toMember(request, account));

        return ApiResponse.success();
    }

    

    @Override
    public ApiResponse<MemberResponse> getById(Integer id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return ApiResponse.success(null,MemberMapper.toMemberResponse(member));
    }

    @Override
    public ApiResponse<List<MemberResponse>> getAll() {

        List<MemberResponse> responses = memberRepository.findAll()
                .stream()
                .map(MemberMapper::toMemberResponse)
                .toList();

        return ApiResponse.success(null,responses);
    }
    
//    @Override
//    public ApiResponse<Void> update(Integer id, MemberRequest request) {
//
//        Member member = memberRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Member not found"));
//
//        if (!member.getCardNumber().equals(request.getCardNumber())
//                && memberRepository.existsByCardNumber(request.getCardNumber())) {
//            throw new RuntimeException("Card number already exists");
//        }
//
//        Account account = accountRepository.findById(request.getCreatedBy())
//                .orElseThrow(() -> new RuntimeException("Account not found"));
//
//        member.setCardNumber(request.getCardNumber());
//        member.setName(request.getName());
//        member.setCreatedBy(account);
//        member.setUpdatedAt(LocalDateTime.now());
//
//        memberRepository.save(member);
//
//        return ApiResponse.success();
//    }
    
//    @Override
//    public ApiResponse<Void> delete(Integer id) {
//
//        Member member = memberRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Member not found"));
//
//        LocalDateTime now = LocalDateTime.now();
//
//        member.setDeleteAt(now);
//        member.setUpdatedAt(now);
//
//        memberRepository.save(member);
//
//        return ApiResponse.success();
//    }

}