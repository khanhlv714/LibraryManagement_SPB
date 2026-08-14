package com.example.myapplication.feature.librarian.member

import com.example.myapplication.domain.model.Member

data class MemberUiState(
    var loading : Boolean = false,
    var error : String? = null,
    var memberList: List<Member> = listOf()
)

