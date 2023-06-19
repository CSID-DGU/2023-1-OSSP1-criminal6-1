package com.example.testapplication.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class searchroomrequest(
    @SerialName("activity")
    val activity: String,
    @SerialName("area1")
    val area1: String,
    @SerialName("area2")
    val area2: String,
    @SerialName("area3")
    val area3: String,
    @SerialName("difficulty")
    val difficulty: String,
    @SerialName("enddate")
    val enddate: String,
    @SerialName("fear")
    val fear: String,
    @SerialName("genre")
    val genre: String,
    @SerialName("startdate")
    val startdate: String
)