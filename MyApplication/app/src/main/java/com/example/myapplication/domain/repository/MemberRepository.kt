package com.example.myapplication.domain.repository

import androidx.paging.PagingData
import com.example.myapplication.core.common.Resource
import com.example.myapplication.data.remote.api.MemberApi
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Member
import com.example.myapplication.domain.model.MemberLoanStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MemberRepository{
    suspend fun getMembers(): Resource<List<Member>>
    fun observeMembers(name : String,memberStatus : MemberLoanStatus): Flow<PagingData<Member>>
}