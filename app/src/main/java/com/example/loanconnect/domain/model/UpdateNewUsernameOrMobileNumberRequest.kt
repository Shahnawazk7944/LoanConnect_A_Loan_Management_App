package com.example.loanconnect.domain.model

import com.google.gson.annotations.SerializedName

data class UpdateUsernameRequest(

    @SerializedName("mobile")
    val mobile: String,

    @SerializedName("username")
    val username: String,
)

data class UpdateMobileNumberRequest(

    @SerializedName("mobile")
    val mobile: String,

    @SerializedName("new_mobile")
    val new_mobile: String,
)