package com.example.loanconnect.presentation.states

sealed class AppEvents {
    data class ApplyForLoan(val contactNumber: String, val amount: Int, val duration: Int) : AppEvents()
    data class ErrorShown(val showError: Boolean,val removeError: String? = null) : AppEvents()
}