package com.example.myapplication.domain.usecase.member

import androidx.paging.PagingData
import com.example.myapplication.domain.model.Member
import com.example.myapplication.domain.model.MemberLoanStatus
import com.example.myapplication.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMembers @Inject constructor(
    private val repository: MemberRepository
) {
    fun observe(name : String,memberStatus : MemberLoanStatus): Flow<PagingData<Member>> {
        return repository.observeMembers(name,memberStatus)
    }
}