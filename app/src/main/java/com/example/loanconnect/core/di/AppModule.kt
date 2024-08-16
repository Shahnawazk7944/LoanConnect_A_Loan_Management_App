package com.example.loanconnect.core.di

import com.example.loanconnect.core.util.BASE_URL
import com.example.loanconnect.data.remote.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Provides
//    fun provideGson(): Gson {
//        return GsonBuilder()
//            .serializeNulls() // Example customization
//            .create()
//    }
//
//    @Provides
//    @Singleton
//    fun providesOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder().build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//    }
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}


//@Module
//@InstallIn(SingletonComponent::class)
//class AppModule {
//
//    @Provides
//    fun provideGson(): Gson {
//        return Gson()
//    }
//
//    @Provides
//    @Singleton
//    fun providesOkHttpClient(
//    ): OkHttpClient {
//        return OkHttpClient.Builder().build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideAuthApiService(retrofit: Retrofit): ApiService =
//        retrofit.create(ApiService::class.java)
//}