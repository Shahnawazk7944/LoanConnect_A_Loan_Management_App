package com.example.loanconnect.presentation.states

data class AppStates(
    val applyingForLoan: Boolean = false,
    val appliedForLoanSuccessMessage: String? = null,
    val showError: Boolean = false,
    val error: String? = null,
)