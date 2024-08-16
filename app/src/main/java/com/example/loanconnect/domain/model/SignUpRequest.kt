package com.example.loanconnect.domain.model

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
   @SerializedName("username")
    val username: String,

   @SerializedName("password")
    val password: String,

   @SerializedName("mobile")
    val mobile: String
)