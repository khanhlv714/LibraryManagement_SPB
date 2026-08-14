package com.example.myapplication.data.repository

import com.example.myapplication.core.util.requireBody
import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.network.ApiResponseHandler
import com.example.myapplication.core.network.handleNetworkException
import com.example.myapplication.data.mapper.MemberMapper.toMember
import com.example.myapplication.data.remote.api.MemberApi
import com.example.myapplication.data.remote.dto.request.MemberRequest
import com.example.myapplication.data.remote.dto.response.MemberResponse
import com.example.myapplication.domain.model.Member
import com.example.myapplication.domain.repository.MemberRepository
import com.google.gson.Gson
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberApi: MemberApi
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
