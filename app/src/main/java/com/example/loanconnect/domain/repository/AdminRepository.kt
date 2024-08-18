package com.example.loanconnect.domain.repository

import arrow.core.Either
import com.example.loanconnect.domain.model.AdminFailedResponse
import com.example.loanconnect.domain.model.AppFailedResponse
import com.example.loanconnect.domain.model.AppResponse
import com.example.loanconnect.domain.model.LoanRequest
import com.example.loanconnect.domain.model.UpdateMobileNumberRequest
import com.example.loanconnect.domain.model.UpdateUsernameRequest
import com.example.loanconnect.domain.model.UploadPhotoRequest
import com.example.loanconnect.domain.model.UsersData

interface AdminRepository {
    suspend fun getUsersData(): Either<AdminFailedResponse, MutableList<UsersData>>

}