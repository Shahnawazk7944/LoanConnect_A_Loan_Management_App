package com.example.loanconnect.data.remote

import com.example.loanconnect.domain.model.AppResponse
import com.example.loanconnect.domain.model.AuthResponse
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.model.SignInRequest
import com.example.loanconnect.domain.model.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("/sign_up")
    @Headers("Content-Type: application/json", "Accept: application/json", "Connection: close")
    suspend fun signUpRequest(@Body request: SignUpRequest): AuthResponse

    @POST("/sign_in")
    @Headers("Content-Type: application/json", "Accept: application/json", "Connection: close")
    suspend fun signInRequest(@Body request: SignInRequest): AuthResponse

    @POST("/apply_loan")
    @Headers("Content-Type: application/json", "Accept: application/json", "Connection: close")
    suspend fun applyForLoan(@Body request: LoanRequest): Response<AppResponse>
}