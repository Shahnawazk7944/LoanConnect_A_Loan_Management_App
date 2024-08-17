package com.example.loanconnect.data.repository

import arrow.core.Either
import com.example.loanconnect.data.remote.ApiService
import com.example.loanconnect.domain.model.AuthFailedResponse
import com.example.loanconnect.domain.model.AuthResponse
import com.example.loanconnect.domain.model.SignInRequest
import com.example.loanconnect.domain.model.SignUpRequest
import com.example.loanconnect.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val apiService: ApiService) : AuthRepository {

    override suspend fun signUpRequest(signUpRequest: SignUpRequest): Either<AuthFailedResponse, AuthResponse> {
        return try {

            val response = apiService.signUpRequest(signUpRequest)
            return Either.Right(response)

        } catch (e: Exception) {
            Either.Left(AuthFailedResponse(message = "User Already Exists or ${e.message}"))
        }
    }

    override suspend fun signInRequest(signInRequest: SignInRequest): Either<AuthFailedResponse, AuthResponse> {
        return try {

            val response = apiService.signInRequest(signInRequest)
            return Either.Right(response)

        } catch (e: Exception) {
            Either.Left(AuthFailedResponse(message = "Invalid Details or ${e.message}"))
        }
    }
}
