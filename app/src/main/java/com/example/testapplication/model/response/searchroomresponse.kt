package com.example.testapplication.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class searchroomresponse(
    @SerialName("success")
    val success: Boolean,
)