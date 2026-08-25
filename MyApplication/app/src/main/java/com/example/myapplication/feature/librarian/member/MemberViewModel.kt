package com.example.myapplication.feature.librarian.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.Member
import com.example.myapplication.domain.model.MemberLoanStatus
import com.example.myapplication.domain.usecase.member.ObserveMembers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
@HiltViewModel

class MemberViewModel @Inject constructor(
    private val observeMembers: ObserveMembers
) : ViewModel() {

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    private val _memberStatus = MutableStateFlow<MemberLoanStatus>(MemberLoanStatus.NO_BORROWING)
    val memberStatus = _memberStatus.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    private val isRefreshing = _isRefreshing.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    private val error = _error.asStateFlow()


    val memberFilter: Flow<PagingData<Member>> =
        combine(
            memberStatus,
            search,
        ) { memberStatus, search ->
            memberStatus to search
        }.flatMapLatest{ (memberStatus, search) ->
            observeMembers.observe(search,memberStatus)
        }.cachedIn(viewModelScope)



    fun search(value: String) {
        _search.value = value
    }
    fun setMemberStatus(value: MemberLoanStatus) {
        _memberStatus.value = value
    }


}