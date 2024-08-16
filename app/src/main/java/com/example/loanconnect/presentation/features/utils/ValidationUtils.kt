package com.example.loanconnect.presentation.features.utils

import android.util.Patterns

object ValidationUtils {
    fun isValidContactNumber(contactNumber: String): Boolean {
        return Patterns.PHONE.matcher(contactNumber).matches() && contactNumber.length == 10
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 12
    }

    fun isValidUserName(userName: String): Boolean {
        return userName.length >= 3
    }

    fun isValidLoanAmount(amount: String): Boolean {
        return if (amount.isEmpty()) {
            false
        } else {
            return amount.length >= 5 && amount.length <= 10
        }
    }

    fun isValidLoanDuration(duration: String): Boolean {
        return if (duration.isEmpty()) {
            false
        } else {
            duration.toInt() >= 12 && duration.toInt() <= 120
        }

    }
}