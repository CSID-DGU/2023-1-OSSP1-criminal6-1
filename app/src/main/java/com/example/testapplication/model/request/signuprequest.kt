package com.example.testapplication.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class signuprequest(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("password")
    val password: String
)