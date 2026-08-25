package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.entity.LoanSlipEntity
import com.example.myapplication.data.remote.dto.response.LoanSlipResponse
import com.example.myapplication.data.remote.dto.response.LoanSlipSyncResponse
import com.example.myapplication.domain.model.LoanSlip
import java.time.format.DateTimeFormatter


object LoanSlipMapper {
    fun LoanSlipResponse.toLoanSlip(): LoanSlip{
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LoanSlip(id,receiptNumber,bookCode,bookName,memberName,memberCardNumber,state,borrowDate.format(formatter),dueDate.format(formatter));
    }
    fun LoanSlipSyncResponse.toEntity() = LoanSlipEntity(
        id = id, receiptNumber = receiptNumber, accountId = accountId, bookId = bookId, memberId = memberId, states = state, updatedAt = updatedAt, deleteAt = deleteAt, borrowDate = borrowDate, dueDate = dueDate,version
    )
}