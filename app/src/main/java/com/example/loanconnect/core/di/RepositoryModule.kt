package com.example.loanconnect.core.di

import com.example.loanconnect.data.repository.AdminRepositoryImpl
import com.example.loanconnect.data.repository.AppRepositoryImpl
import com.example.loanconnect.data.repository.AuthRepositoryImpl
import com.example.loanconnect.domain.repository.AdminRepository
import com.example.loanconnect.domain.repository.AppRepository
import com.example.loanconnect.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindAppRepository(impl: AppRepositoryImpl): AppRepository

    @Binds
    @Singleton
    abstract fun bindAdminRepository(impl: AdminRepositoryImpl): AdminRepository
}