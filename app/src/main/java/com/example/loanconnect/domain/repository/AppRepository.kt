package com.example.loanconnect.domain.repository

import arrow.core.Either
import com.example.loanconnect.domain.model.AppFailedResponse
import com.example.loanconnect.domain.model.AppResponse
import com.example.loanconnect.domain.model.AuthFailedResponse
import com.example.loanconnect.domain.model.AuthResponse
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.model.SignInRequest
import com.example.loanconnect.domain.model.SignUpRequest
import com.example.loanconnect.domain.model.UpdateMobileNumberRequest
import com.example.loanconnect.domain.model.UpdateUsernameRequest

interface AppRepository {
    suspend fun applyForLoan(loanRequest: LoanRequest): Either<AppFailedResponse, AppResponse>
    suspend fun updateUsername(updateUsernameRequest: UpdateUsernameRequest): Either<AppFailedResponse, AppResponse>
    suspend fun updateMobileNumber(updateMobileNumberRequest: UpdateMobileNumberRequest): Either<AppFailedResponse, AppResponse>
}