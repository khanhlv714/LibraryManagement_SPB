package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.database.AppDatabase
import com.example.myapplication.data.local.dao.BookDao
import com.example.myapplication.data.local.dao.CategoryDao
import com.example.myapplication.data.local.dao.LoanSlipDao
import com.example.myapplication.data.local.dao.MemberDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "library_database"
        ).build()

    }

    @Provides
    fun provideBookDao(
        database: AppDatabase
    ): BookDao {

        return database.bookDao()

    }

    @Provides
    fun provideCategoryDao(
        database: AppDatabase
    ): CategoryDao {

        return database.categoryDao()

    }

    @Provides
    fun provideMemberDao(
        database: AppDatabase
    ): MemberDao {

        return database.memberDao()

    }

    @Provides
    fun provideLoanSlipDao(
        database: AppDatabase
    ): LoanSlipDao {

        return database.loanSlipDao()

    }

}