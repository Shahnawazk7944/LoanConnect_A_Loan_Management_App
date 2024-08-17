package com.example.loanconnect.presentation.states

data class AuthStates(
    val loading: Boolean = false,
    val showError: Boolean = false,
    val error: String? = null,
    val isLoggedIn: Boolean = false,
    val username: String? = "Shahaaam",
    val contactNumber: String = "0101010108",
 //   val contactNumber: String = "0101010101",
)