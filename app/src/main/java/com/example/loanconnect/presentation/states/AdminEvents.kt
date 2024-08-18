package com.example.loanconnect.presentation.states

sealed class AdminEvents {
    data class GetUserData(val getUserData: Boolean) : AdminEvents()
    data class ErrorShown(val showError: Boolean,val removeError: String? = null) : AdminEvents()

}