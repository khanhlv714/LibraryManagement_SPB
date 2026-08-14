package com.example.myapplication.domain.usecase.member

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.Member
import com.example.myapplication.domain.repository.MemberRepository
import javax.inject.Inject

class GetMembersUseCase @Inject constructor(
    private val repository: MemberRepository
) {
    suspend operator fun invoke(): Resource<List<Member>> {
        return repository.getMembers()
    }
}