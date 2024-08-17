package com.example.loanconnect.data.repository

import arrow.core.Either
import com.example.loanconnect.data.remote.ApiService
import com.example.loanconnect.domain.model.AppFailedResponse
import com.example.loanconnect.domain.model.AppResponse
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val apiService: ApiService) : AppRepository {


    override suspend fun applyForLoan(loanRequest: LoanRequest): Either<AppFailedResponse, AppResponse> {
        return try {

            val response = apiService.applyForLoan(loanRequest)

            return if (response.code() == 201) {
                Either.Right(response.body()!!)
            } else {
                Either.Left(AppFailedResponse(message = response.message()))
            }

        } catch (e: Exception) {
            Either.Left(AppFailedResponse(message = "User Not Found or ${e.message}"))
        }
    }


}
