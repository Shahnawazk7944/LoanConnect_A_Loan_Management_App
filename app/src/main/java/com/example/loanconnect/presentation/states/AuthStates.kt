package com.example.loanconnect.presentation.states

data class AuthStates(
    val loading: Boolean = false,
    val showError: Boolean = false,
    val error: String = "",
)