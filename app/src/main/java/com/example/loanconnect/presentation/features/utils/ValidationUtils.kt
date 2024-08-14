package com.example.loanconnect.presentation.features.utils

import android.util.Patterns

object ValidationUtils {
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    fun isValidUserName(userName: String): Boolean {
        return userName.length >= 3
    }
}