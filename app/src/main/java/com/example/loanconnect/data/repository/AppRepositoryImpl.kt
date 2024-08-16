package com.example.loanconnect.data.repository

import arrow.core.Either
import com.example.loanconnect.data.remote.ApiService
import com.example.loanconnect.domain.model.AuthFailedResponse
import com.example.loanconnect.domain.model.AuthResponse
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.model.SignInRequest
import com.example.loanconnect.domain.model.SignUpRequest
import com.example.loanconnect.domain.repository.AppRepository
import com.example.loanconnect.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val apiService: ApiService) : AppRepository {


    override suspend fun applyForLoan(loanRequest: LoanRequest): Either<AuthFailedResponse, AuthResponse> {
        return try {

            val response = apiService.applyForLoan(loanRequest)
            Either.Right(response)

        } catch (e: Exception) {
            Either.Left(AuthFailedResponse(message = "User Not Found or ${e.message}"))
        }
    }


}
