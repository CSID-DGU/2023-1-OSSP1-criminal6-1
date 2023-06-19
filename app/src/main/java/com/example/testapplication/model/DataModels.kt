package com.example.testapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserModel(
    @Expose
    @SerializedName("result") //json에서 사용중인 key값 서버에서 받아오는 키값이랑 같아야함
    var result: String? = null
)

data class UserSignupPostModel(
    @Expose
    @SerializedName("id")
    var id: String? = null,

    @Expose
    @SerializedName("password")
    var password: String? = null,

    @Expose
    @SerializedName("name")
    var name: String? = null,

    @Expose
    @SerializedName("result")
    var result: String? = null
)

data class UserSigninModel(
    @Expose
    @SerializedName("id")
    var id: String? = null,

    @Expose
    @SerializedName("password")
    var password: String? = null,

    @Expose
    @SerializedName("result")
    var result: String? = null
)

data class createRoomPostModel(
    @Expose
    @SerializedName("createrUserId")
    var createrUserId: String? = null,

    @Expose
    @SerializedName("title")
    var title: String? = null,

    @Expose
    @SerializedName("region1")
    var region1: String? = null,

    @Expose
    @SerializedName("region2")
    var region2: String? = null,

    @Expose
    @SerializedName("region3")
    var region3: String? = null,

    @Expose
    @SerializedName("dateFrom")
    var dateFrom: String? = null,

    @Expose
    @SerializedName("dateTo")
    var dateTo: String? = null,

    @Expose
    @SerializedName("genre")
    var genre: String? = null,

    @Expose
    @SerializedName("difficulty")
    var difficulty: Int? = null,

    @Expose
    @SerializedName("fear")
    var fear: Int? = null,

    @Expose
    @SerializedName("activity")
    var activity: Int? = null,

    @Expose
    @SerializedName("roomIntro")
    var roomIntro: String? = null,

    @Expose
    @SerializedName("result")
    var result: String? = null
)

data class getRoomListModel(
    @Expose
    @SerializedName("roomList")
    var roomList: List<roomData>
) {
    data class roomData(
        @Expose
        @SerializedName("roomID")
        var roomID: String? = null,

        @Expose
        @SerializedName("title")
        var title: String? = null,
    )
}

data class getAllRoomInfoModel(
    @Expose
    @SerializedName("roomInfoList")
    var roomInfoList: List<allRoomInfo>
) {
    data class allRoomInfo(
        @Expose
        @SerializedName("roomID")
        var roomID: Int? = null,

        @Expose
        @SerializedName("title")
        var title: String? = null,

        @Expose
        @SerializedName("region1")
        var region1: String? = null,

        @Expose
        @SerializedName("region2")
        var region2: String? = null,

        @Expose
        @SerializedName("region3")
        var region3: String? = null,

        @Expose
        @SerializedName("date")
        var date: String? = null,

        @Expose
        @SerializedName("genre")
        var genre: String? = null,

        @Expose
        @SerializedName("difficulty")
        var difficulty: Int? = null,

        @Expose
        @SerializedName("fear")
        var fear: Int? = null,

        @Expose
        @SerializedName("activity")
        var activity: Int? = null
    )
}

data class getRoomRecommendationModel(
    @Expose
    @SerializedName("roomInfoList")
    var roomInfoList: List<recommendationRoomInfo>
) {
    data class recommendationRoomInfo(
        @Expose
        @SerializedName("id")
        var id: String? = null,

        @Expose
        @SerializedName("roomID")
        var roomID: Int? = null,

        @Expose
        @SerializedName("title")
        var title: String? = null,

        @Expose
        @SerializedName("roomIntro")
        var roomIntro: String? = null
    )
}

data class addUserToChatRoomModel(
    @Expose
    @SerializedName("id")
    var id: String? = null,

    @Expose
    @SerializedName("roomID")
    var roomID: Int? = null
)

data class postRecommendationList(
    @Expose
    @SerializedName("id")
    var id: String? = null,

    @Expose
    @SerializedName("roomID1")
    var roomNum1: Int? = null,

    @Expose
    @SerializedName("roomID2")
    var roomNum2: Int? = null,

    @Expose
    @SerializedName("roomID3")
    var roomNum3: Int? = null
)

data class getRecommendationList(
    @Expose
    @SerializedName("result2")
    var result: String? = null

//    @Expose
//    @SerializedName("roomID1")
//    var roomNum1: Int? = null,
//
//    @Expose
//    @SerializedName("roomID2")
//    var roomNum2: Int? = null,
//
//    @Expose
//    @SerializedName("roomID3")
//    var roomNum3: Int? = null,
)

data class getAllChat(
    @Expose
    @SerializedName("result2")
    var result: String? = null
)