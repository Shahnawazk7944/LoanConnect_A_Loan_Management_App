package com.example.loanconnect.domain.model

import com.google.gson.annotations.SerializedName

data class UploadPhotoRequest(
    @SerializedName("mobile")
    val mobile: String,

    @SerializedName("photo")
    val photo: String
)