package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.dto.response.LoanSlipResponse
import com.example.myapplication.domain.model.LoanSlip
import java.time.format.DateTimeFormatter


object LoanSlipMapper {
    fun LoanSlipResponse.toLoanSlip(): LoanSlip{
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LoanSlip(id,receiptNumber,bookCode,bookName,memberName,memberCardNumber,state,borrowDate.format(formatter),dueDate.format(formatter));
    }
}