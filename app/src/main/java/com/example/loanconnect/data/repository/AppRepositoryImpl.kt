package com.example.loanconnect.data.repository

import android.util.Log
import arrow.core.Either
import com.example.loanconnect.data.remote.ApiService
import com.example.loanconnect.domain.model.AppFailedResponse
import com.example.loanconnect.domain.model.AppResponse
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.model.UpdateMobileNumberRequest
import com.example.loanconnect.domain.model.UpdateUsernameRequest
import com.example.loanconnect.domain.model.UploadPhotoRequest
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

    override suspend fun updateUsername(updateUsernameRequest: UpdateUsernameRequest): Either<AppFailedResponse, AppResponse> {
        return try {

            val response = apiService.updateUsername(updateUsernameRequest)
            return if (response.isFailure) {
                Either.Left(AppFailedResponse(message = "User Not Found or ${response.exceptionOrNull()?.message}"))
            } else {
                Either.Right(response.getOrNull()!!)
            }

        } catch (e: Exception) {

            Either.Left(AppFailedResponse(message = "User Not Found or $e"))
        }
    }

    override suspend fun updateMobileNumber(updateMobileNumberRequest: UpdateMobileNumberRequest): Either<AppFailedResponse, AppResponse> {
        return try {

            val response = apiService.updateMobileNumber(updateMobileNumberRequest)
            return if (response.isFailure) {
                Either.Left(AppFailedResponse(message = "User Not Found or ${response.exceptionOrNull()?.message}"))
            } else {
                Either.Right(response.getOrNull()!!)
            }

        } catch (e: Exception) {
            Either.Left(AppFailedResponse(message = "User Not Found or ${e.message}"))
        }
    }

    override suspend fun uploadPhoto(uploadPhotoRequest: UploadPhotoRequest): Either<AppFailedResponse, AppResponse> {
        return try {
            val response = apiService.uploadPhoto(uploadPhotoRequest)
            Either.Right(response)

        } catch (e: Exception) {
            Either.Left(AppFailedResponse(message = "User Not Found or ${e.message}"))
        }
    }

}
