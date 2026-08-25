package com.example.myapplication.data.repository

import androidx.room.withTransaction
import com.example.myapplication.core.common.Resource
import com.example.myapplication.core.datastore.DatabaseVersionManager
import com.example.myapplication.core.network.ApiResponseHandler
import com.example.myapplication.core.network.handleNetworkException
import com.example.myapplication.data.local.dao.BookDao
import com.example.myapplication.data.local.dao.CategoryDao
import com.example.myapplication.data.local.dao.LoanSlipDao
import com.example.myapplication.data.local.dao.MemberDao
import com.example.myapplication.data.local.database.AppDatabase
import com.example.myapplication.data.mapper.LoanSlipMapper.toEntity
import com.example.myapplication.data.mapper.MemberMapper.toEntity
import com.example.myapplication.data.mapper.bookMapper.toEntity
import com.example.myapplication.data.mapper.categoryMapper.toEntity
import com.example.myapplication.data.remote.api.SyncApi
import com.example.myapplication.domain.repository.SyncRepository
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.streams.toList

class SyncRepositoryImpl @Inject constructor(
    private val db: AppDatabase, val api: SyncApi, val bookDao: BookDao,
    val categoryDao: CategoryDao, val loanSlipDao: LoanSlipDao, val memberDao: MemberDao, val manager: DatabaseVersionManager
) : SyncRepository {
    override suspend fun syncData(): Resource<Unit> {
        return try {

            val time = manager.getUpdateTime()
            val res = if (time != null) {
                api.syncDataToRoom(time)
            } else {
                api.initDataBaseRoom()
            }

            val result = ApiResponseHandler.handle(res)
            when (result) {
                is Resource.Success -> {
                    val data = result.data
                    db.withTransaction {

                        categoryDao.insertCategories(data.categories.map{ it.toEntity()})
                        bookDao.insertBooks(data.books.map { it.toEntity() })

                        memberDao.insertMembers(data.members.map { it.toEntity() })

                        loanSlipDao.insertLoanSlips(data.loanSlips.map { it.toEntity() })
                    }

                    val time = data.timeSync.format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    )

                    manager.saveUpdateTime(time)

                    return Resource.Success(Unit)
                }

                is Resource.Error -> return result
            }
        } catch (e: Exception) {
            handleNetworkException(e);
        }
    }

}