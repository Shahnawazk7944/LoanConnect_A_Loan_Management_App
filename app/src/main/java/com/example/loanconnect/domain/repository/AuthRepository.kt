package com.example.loanconnect.domain.repository

import arrow.core.Either
import com.example.loanconnect.domain.model.AuthFailedResponse
import com.example.loanconnect.domain.model.AuthResponse
import com.example.loanconnect.domain.model.SignInRequest
import com.example.loanconnect.domain.model.SignUpRequest

interface AuthRepository {
    suspend fun signUpRequest(signUpRequest: SignUpRequest): Either<AuthFailedResponse, AuthResponse>
    suspend fun signInRequest(signInRequest: SignInRequest): Either<AuthFailedResponse, AuthResponse>
}