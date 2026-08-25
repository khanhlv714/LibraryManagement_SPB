package com.example.myapplication.di

import com.example.myapplication.data.repository.AuthRepositoryImpl
import com.example.myapplication.data.repository.BookRepositoryImpl
import com.example.myapplication.data.repository.CategoryRepositoryImpl
import com.example.myapplication.data.repository.LoanSlipRepositoryImpl
import com.example.myapplication.data.repository.MemberRepositoryImpl
import com.example.myapplication.data.repository.SyncRepositoryImpl
import com.example.myapplication.domain.repository.AuthRepository
import com.example.myapplication.domain.repository.BookRepository
import com.example.myapplication.domain.repository.CategoryRepository
import com.example.myapplication.domain.repository.LoanSlipRepository
import com.example.myapplication.domain.repository.MemberRepository
import com.example.myapplication.domain.repository.SyncRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindBookRepository(
        impl:  BookRepositoryImpl
    ): BookRepository

    @Binds
    abstract fun bindCategoryRepository(
        impl:  CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    abstract fun bindMemberRepository(
        impl:  MemberRepositoryImpl
    ): MemberRepository

    @Binds
    abstract fun bindLoanSlipReposiroty(
        impl:  LoanSlipRepositoryImpl
    ): LoanSlipRepository

    @Binds
    abstract fun bindSyncReposiroty(
        impl:  SyncRepositoryImpl
    ): SyncRepository
}