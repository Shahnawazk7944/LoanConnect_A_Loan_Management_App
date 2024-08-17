package com.example.loanconnect.presentation.states

data class AppStates(
    val applyingForLoan: Boolean = false,
    val appliedForLoanSuccessMessage: String? = null,
    val updatingNewUsernameSuccessMessage: String? = null,
    val updatingNewMobileNumberSuccessMessage: String? = null,
    val showError: Boolean = false,
    val error: String? = null,
    val updatingNewUsername: Boolean = false,
    val updatingNewMobileNumber: Boolean = false,

)