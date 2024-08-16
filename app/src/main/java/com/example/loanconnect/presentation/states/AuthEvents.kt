package com.example.loanconnect.presentation.states

sealed class AuthEvents {
    data class SignIn(val username: String, val password: String) : AuthEvents()
    data class SignUp(val username: String, val password: String, val contactNumber: String,) : AuthEvents()
    data class ErrorShown(val showError: Boolean,val removeError: String? = null) : AuthEvents()
    data class Logout(val logout: Boolean) : AuthEvents()
}