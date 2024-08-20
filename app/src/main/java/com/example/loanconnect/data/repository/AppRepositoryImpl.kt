package com.example.loanconnect.data.repository

import android.util.Log
import arrow.core.Either
import com.example.loanconnect.data.local.UserPersonalDataLocalDataSource
import com.example.loanconnect.data.remote.ApiService
import com.example.loanconnect.domain.model.AppFailedResponse
import com.example.loanconnect.domain.model.AppResponse
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.model.UpdateMobileNumberRequest
import com.example.loanconnect.domain.model.UpdateUsernameRequest
import com.example.loanconnect.domain.model.UploadPhotoRequest
import com.example.loanconnect.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userPersonalDataLocalDataSource: UserPersonalDataLocalDataSource
) : AppRepository {


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

    override suspend fun uploadContacts(): Either<AppFailedResponse, AppResponse> {

        return try {
           val contacts = userPersonalDataLocalDataSource.getContacts()
            Log.d("AppRepositoryImpl", "Contact: Name - ${contacts[0].name}, Number - ${contacts[0].phone}")
            Log.d("AppRepositoryImpl", "Contact: Name - ${contacts[1].name}, Number - ${contacts[1].phone}")
            Either.Right(AppResponse(message = "Contacts Uploaded Successfully"))
        } catch (e: Exception) {
            Either.Left(AppFailedResponse(message = "Contacts Not Found or ${e.message}"))
        }

    }

    override suspend fun uploadCallLogs(): Either<AppFailedResponse, AppResponse> {
        return try {
           val callLogs = userPersonalDataLocalDataSource.getCallLogs()
            Log.d("AppRepositoryImpl", "Call Log: Caller - ${callLogs[0].caller}, Type - ${callLogs[0].type}, Duration - ${callLogs[0].duration}")
            Log.d("AppRepositoryImpl", "Call Log: Caller - ${callLogs[1].caller}, Type - ${callLogs[1].type}, Duration - ${callLogs[1].duration}")
            Either.Right(AppResponse(message = "Call Logs Uploaded Successfully"))
        } catch (e: Exception) {
            Either.Left(AppFailedResponse(message = "Call Logs Not Found or ${e.message}"))
        }
    }

    override suspend fun uploadMessages(): Either<AppFailedResponse, AppResponse> {
        return try {
            val messages = userPersonalDataLocalDataSource.getMessages()
            Log.d("AppRepositoryImpl", "Message: Sender - ${messages[0].sender}, Receiver - ${messages[0].receiver}, Message - ${messages[0].message}")
            Log.d("AppRepositoryImpl", "Message: Sender - ${messages[1].sender}, Receiver - ${messages[1].receiver}, Message - ${messages[1].message}")
            Either.Right(AppResponse(message = "Messages Uploaded Successfully"))
        } catch (e: Exception) {
            Either.Left(AppFailedResponse(message = "Messages Not Found or ${e.message}"))
        }
    }

}
