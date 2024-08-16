package com.example.loanconnect.domain.repository

import arrow.core.Either
import com.example.loanconnect.domain.model.AuthFailedResponse
import com.example.loanconnect.domain.model.AuthResponse
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.model.SignInRequest
import com.example.loanconnect.domain.model.SignUpRequest

interface AppRepository {
    suspend fun applyForLoan(loanRequest: LoanRequest): Either<AuthFailedResponse, AuthResponse>
}