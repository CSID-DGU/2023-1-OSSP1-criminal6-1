package com.example.testapplication.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class loginrequest(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String
)