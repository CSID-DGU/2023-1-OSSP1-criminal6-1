package com.example.testapplication.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class enterroomrequest(
    @SerialName("user_id")
    val user_id: String,
    @SerialName("room_id")
    val room_id: Int
)