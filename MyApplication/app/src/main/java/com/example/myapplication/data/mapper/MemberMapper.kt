package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.dto.response.BookResponse
import com.example.myapplication.data.remote.dto.response.MemberResponse
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Member

object MemberMapper {
    fun MemberResponse.toMember(): Member {
        return Member(id,cardNumber,name)
    }
}