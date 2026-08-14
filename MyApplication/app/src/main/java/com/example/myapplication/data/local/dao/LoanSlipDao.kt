package com.example.myapplication.data.local.dao

import androidx.room.*
import com.example.myapplication.data.local.entity.LoanSlipEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LoanSlipDao {

    @Query("SELECT * FROM LoanSlip")
    fun getAll(): Flow<List<LoanSlipEntity>>

    @Query("SELECT * FROM LoanSlip WHERE id = :id")
    suspend fun getLoanSlipById(id: Int): LoanSlipEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoanSlip(loanSlip: LoanSlipEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoanSlips(loanSlips: List<LoanSlipEntity>)

    @Update
    suspend fun updateLoanSlip(loanSlip: LoanSlipEntity)

    @Delete
    suspend fun deleteLoanSlip(loanSlip: LoanSlipEntity)

    @Query("DELETE FROM LoanSlip")
    suspend fun deleteAllLoanSlips()
}