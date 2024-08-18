package com.example.loanconnect.presentation.states

import android.graphics.Bitmap

data class AppStates(
    val applyingForLoan: Boolean = false,
    val appliedForLoanSuccessMessage: String? = null,
    val updatedNewUsernameSuccessMessage: String? = null,
    val updatedNewMobileNumberSuccessMessage: String? = null,
    val showError: Boolean = false,
    val error: String? = null,
    val updatingNewUsername: Boolean = false,
    val updatingNewMobileNumber: Boolean = false,
    val imageUri: String? = null,
    val uploadingPhoto: Boolean = false,
    val photoUploadedSuccessMessage: String? = null,
    val bitmap: Bitmap? = null
)