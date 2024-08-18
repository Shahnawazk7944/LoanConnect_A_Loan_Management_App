package com.example.loanconnect.presentation.states

import android.graphics.Bitmap

sealed class AppEvents {
    data class ApplyForLoan(val contactNumber: String, val amount: Int, val duration: Int) : AppEvents()
    data class ErrorShown(val showError: Boolean,val removeError: String? = null) : AppEvents()
    data class UpdateNewUsername(val newData: String, val mobileNumberAsId: String) : AppEvents()
    data class UpdateNewMobileNumber(val newData: String, val mobileNumberAsId: String) : AppEvents()
    data class UploadPhoto(val base64String: String, val mobileNumberAsId: String) : AppEvents()
    data class UpdateImageUri(val newUri: String? ) : AppEvents()
    data class SaveBitmap(val bitmap: Bitmap? ) : AppEvents()
}