package com.example.loanconnect.domain.model

data class LoanRequest(
    val mobile: String,
    val loan_amount: Int,
    val loan_duration: Int
)