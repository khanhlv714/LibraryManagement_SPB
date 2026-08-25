package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.MemberEntity
import com.example.myapplication.data.remote.dto.response.BookResponse
import com.example.myapplication.data.remote.dto.response.MemberResponse
import com.example.myapplication.data.remote.dto.response.MemberSyncResponse
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Member

object MemberMapper {
    fun MemberResponse.toMember(): Member {
        return Member(id,cardNumber,name)
    }
    fun MemberSyncResponse.toEntity() = MemberEntity(
        id = id, cardNumber = cardNumber, name = name, updatedAt = updatedAt, deleteAt = deleteAt, accountId = createdBy,version
    )
    fun MemberEntity.toMember(): Member {
        return Member(id,cardNumber,name)
    }
}