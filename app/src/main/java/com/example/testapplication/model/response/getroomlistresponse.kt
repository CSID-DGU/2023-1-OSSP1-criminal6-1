package com.example.testapplication.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class getroomlistresponse(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("success")
    val success: Boolean
) {
    @Serializable
    data class Data(
        @SerialName("activity")
        val activity: Double,
        @SerialName("date")
        val date: Int,
        @SerialName("difficulty")
        val difficulty: Double,
        @SerialName("fear")
        val fear: Double,
        @SerialName("genre")
        val genre: Int,
        @SerialName("region")
        val region: String,
        @SerialName("roomID")
        val roomID: Int,
        @SerialName("roomIntro")
        val roomIntro: String,
        @SerialName("title")
        val title: String
    )
}