package com.example.loanconnect.presentation.states

import com.example.loanconnect.domain.model.UsersData

data class AdminStates(
    val loadingAdminData: Boolean = false,
    val appliedForLoanSuccessMessage: String? = null,
    val showError: Boolean = false,
    val error: String? = null,
    val usersData: List<UsersData> = emptyList()
)
