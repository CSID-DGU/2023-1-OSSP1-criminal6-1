package com.example.testapplication.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class loginresponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("user_id")
    val user_id : String
)