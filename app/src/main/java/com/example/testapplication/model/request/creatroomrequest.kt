package com.example.testapplication.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class creatroomrequest(
    @SerialName("user_id")
    val user_id: String,
    @SerialName("activity")
    val activity: String,
    @SerialName("date")
    val date: String,
    @SerialName("difficulty")
    val difficulty: String,
    @SerialName("fear")
    val fear: String,
    @SerialName("genre")
    val genre: String,
    @SerialName("region")
    val region: String,
    @SerialName("roomIntro")
    val roomIntro: String,
    @SerialName("title")
    val title: String
)