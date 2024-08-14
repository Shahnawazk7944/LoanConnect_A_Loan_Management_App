package com.example.loanconnect.presentation.states

sealed class AuthEvents {
    data class SignIn(val email: String, val password: String) : AuthEvents()
    data class SignUp(val username: String, val email: String, val password: String) : AuthEvents()
}