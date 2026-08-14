package com.example.myapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.dao.AccountDao
import com.example.myapplication.data.local.dao.BookDao
import com.example.myapplication.data.local.dao.CategoryDao
import com.example.myapplication.data.local.dao.LoanSlipDao
import com.example.myapplication.data.local.dao.MemberDao
import com.example.myapplication.data.local.entity.AccountEntity
import com.example.myapplication.data.local.entity.BookEntity
import com.example.myapplication.data.local.entity.CategoryEntity
import com.example.myapplication.data.local.entity.LoanSlipEntity
import com.example.myapplication.data.local.entity.MemberEntity

@Database(
    entities = [
        BookEntity::class,
        CategoryEntity::class,
        MemberEntity::class,
        LoanSlipEntity::class,
        AccountEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    abstract fun categoryDao(): CategoryDao

    abstract fun memberDao(): MemberDao

    abstract fun loanSlipDao(): LoanSlipDao

    abstract fun accountDao(): AccountDao


}