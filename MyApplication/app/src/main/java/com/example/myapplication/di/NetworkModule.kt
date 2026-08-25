package com.example.myapplication.di

import com.example.myapplication.core.datastore.SessionManager
import com.example.myapplication.core.datastore.TokenManager
import com.example.myapplication.core.network.ApiInterceptor
import com.example.myapplication.core.network.TokenAuthenticator
import com.example.myapplication.core.util.LocalDateJsonDeserializer
import com.example.myapplication.core.util.LocalTimeDateJsonDeserializer
import com.example.myapplication.data.remote.api.AuthApi
import com.example.myapplication.data.remote.api.BookApi
import com.example.myapplication.data.remote.api.CategoryApi
import com.example.myapplication.data.remote.api.LoanSlipApi
import com.example.myapplication.data.remote.api.MemberApi
import com.example.myapplication.data.remote.api.SyncApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://192.168.75.1:8080/"




    @Provides
    @Singleton
    fun provideAuthApi(loggingInterceptor : HttpLoggingInterceptor): AuthApi {

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(AuthApi::class.java)
    }



    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    }


    @Provides
    @Singleton
    fun provideAuthenticator(
        tokenManager: TokenManager,
        authApi: AuthApi,
        sessionManager: SessionManager
    ): Authenticator {
        return TokenAuthenticator(tokenManager,authApi,sessionManager);

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        apiInterceptor: ApiInterceptor,
        auth : Authenticator

    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(apiInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(auth)
            .build()

    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateJsonDeserializer)
            .registerTypeAdapter(LocalDateTime::class.java, LocalTimeDateJsonDeserializer)
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideBookApi(
        retrofit: Retrofit
    ): BookApi {
        return retrofit.create(BookApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryApi(
        retrofit: Retrofit
    ): CategoryApi {

        return retrofit.create(CategoryApi::class.java)

    }

    @Provides
    @Singleton
    fun provideMemberApi(
        retrofit: Retrofit
    ): MemberApi {

        return retrofit.create(MemberApi::class.java)

    }

    @Provides
    @Singleton
    fun provideLoanSlipApi(
        retrofit: Retrofit
    ): LoanSlipApi {
        return retrofit.create(LoanSlipApi::class.java)

    }

    @Provides
    @Singleton
    fun provideSyncApi(
        retrofit: Retrofit
    ): SyncApi {
        return retrofit.create(SyncApi::class.java)
    }
}
