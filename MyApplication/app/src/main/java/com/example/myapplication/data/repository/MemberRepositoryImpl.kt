package com.example.myapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.myapplication.core.util.requireBody
import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.network.ApiResponseHandler
import com.example.myapplication.core.network.handleNetworkException
import com.example.myapplication.data.local.dao.MemberDao
import com.example.myapplication.data.local.entity.MemberEntity
import com.example.myapplication.data.mapper.MemberMapper.toMember
import com.example.myapplication.data.mapper.bookMapper.toBook
import com.example.myapplication.data.remote.api.MemberApi
import com.example.myapplication.data.remote.dto.request.MemberRequest
import com.example.myapplication.data.remote.dto.response.MemberResponse
import com.example.myapplication.domain.model.Member
import com.example.myapplication.domain.model.MemberLoanStatus
import com.example.myapplication.domain.repository.MemberRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberApi: MemberApi,
    private val memberDao: MemberDao
) : MemberRepository {

    override suspend fun getMembers(): Resource<List<Member>> {
        return try {
            val res = memberApi.getMembers()
            val result = ApiResponseHandler.handle(res)
            when(result) {
                is Resource.Success -> {
                    val data = result.data
                    
                    val members = data.map { item ->
                        item.toMember()
                    }
                    return Resource.Success(members)
                }
                is Resource.Error -> return result
            }

        }catch(e: Exception) {
            handleNetworkException(e)
        }
    }

    override fun observeMembers(name : String,memberStatus : MemberLoanStatus): Flow<PagingData<Member>> {
        var status : Int? = null
        if(memberStatus == MemberLoanStatus.BORROWING) status = 1
         if(memberStatus == MemberLoanStatus.DUE) status = -1
        if(memberStatus == MemberLoanStatus.NO_BORROWING) status = 0

        return Pager(
            config = PagingConfig(
                pageSize = 30, enablePlaceholders = false
            ), pagingSourceFactory = {
                memberDao.pagingMembers(name,status)
            }).flow.map{ pagingData ->
            pagingData.map{ entity ->
                entity.toMember()
            }
        }
    }

    suspend fun getMemberById(id: Int): MemberResponse {
        return memberApi.getMemberById(id).requireBody()
    }

    suspend fun addMember(member: MemberRequest): MemberResponse {
        return memberApi.addMember(member).requireBody()
    }

//    suspend fun updateMember(member: MemberRequest): MemberResponse {
//        return memberApi.updateMember(member.id, member).requireBody()
//    }

    suspend fun deleteMember(id: Int): Boolean {
        return memberApi.deleteMember(id).isSuccessful
    }
}
