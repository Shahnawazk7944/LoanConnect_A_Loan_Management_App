package com.example.loanconnect.core.di

import android.content.Context
import com.example.loanconnect.core.util.BASE_URL
import com.example.loanconnect.data.local.UserPersonalDataLocalDataSource
import com.example.loanconnect.data.remote.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
        //idk what is wrong here
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

//    @Singleton
//    @Provides
//    fun provideContext(@ApplicationContext context: Context): Context {
//        return context
//    }

    @Singleton
    @Provides
    fun provideUserPersonalDataLocalDataSource(@ApplicationContext context: Context): UserPersonalDataLocalDataSource {
        return UserPersonalDataLocalDataSource(context)
    }

//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideAuthApiService(retrofit: Retrofit): ApiService =
//        retrofit.create(ApiService::class.java)
}
