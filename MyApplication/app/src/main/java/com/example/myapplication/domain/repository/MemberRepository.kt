package com.example.myapplication.domain.repository

import com.example.myapplication.core.common.Resource
import com.example.myapplication.data.remote.api.MemberApi
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Member
import javax.inject.Inject

interface MemberRepository{
    suspend fun getMembers(): Resource<List<Member>>
}