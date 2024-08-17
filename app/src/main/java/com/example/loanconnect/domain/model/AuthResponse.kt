package com.example.loanconnect.domain.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("message")
    val message: String
)

data class AppResponse(
    val message: String
)