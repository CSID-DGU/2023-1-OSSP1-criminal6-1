package com.example.testapplication.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class signupresponse(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
)