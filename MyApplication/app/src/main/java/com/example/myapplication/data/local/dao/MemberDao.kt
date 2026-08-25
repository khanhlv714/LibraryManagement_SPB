package com.example.myapplication.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.myapplication.data.local.entity.MemberEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {

    @Query("SELECT * FROM member")
    fun getAllMembers(): Flow<List<MemberEntity>>

    @Query("SELECT * FROM member WHERE id = :id")
    suspend fun getMemberById(id: Int): MemberEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member: MemberEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMembers(members: List<MemberEntity>)

    @Update
    suspend fun updateMember(member: MemberEntity)

    @Delete
    suspend fun deleteMember(member: MemberEntity)

    @Query("DELETE FROM member")
    suspend fun deleteAllMembers()


    @Query(
        """
        SELECT member.id,member.cardNumber,member.name,member.updatedAt,member.deleteAt,member.accountId,member.version FROM member
        inner join loanSlip on memberId = loanSlip.memberId
        WHERE member.deleteAt IS NULL
            AND (
            name LIKE '%' || :info || '%'
            OR cardNumber LIKE '%' || :info || '%'
        ) AND(
        :status is null or loanSlip.states = :status
        ) group by member.id,member.cardNumber,member.name,member.updatedAt,member.deleteAt,member.accountId,member.version
        ORDER BY member.id DESC
    """
    )
    fun pagingMembers(info : String , status : Int?): PagingSource<Int, MemberEntity>

    @Query(
        """
        SELECT * FROM member
        WHERE deleteAt IS NULL
        AND (
            cardNumber LIKE '%' || :keyword || '%'
            OR name LIKE '%' || :keyword || '%'
        )
        ORDER BY id DESC
    """
    )
    fun pagingMembers(
        keyword: String
    ): PagingSource<Int, MemberEntity>

    @Query("SELECT * FROM member WHERE id = :id AND deleteAt IS NULL")
    suspend fun getById(id: Int): MemberEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: MemberEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(members: List<MemberEntity>)

    @Update
    suspend fun update(member: MemberEntity)

    @Query("DELETE FROM member")
    suspend fun deleteAll()
}