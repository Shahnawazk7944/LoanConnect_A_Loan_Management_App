package com.example.loanconnect.domain.model

data class UsersData(
    val userId: Int,
    val username: String,
    val mobile: String,
    val contacts: String?,
    val callLogs: String?,
    val photos: String?,
    val messages: String?,
    var loans: MutableList<UserLoans>
)

data class UserLoans(
    val loanId: Int,
    val loanAmount: Double,
    val duration: String,
    val loanStatus: String
)