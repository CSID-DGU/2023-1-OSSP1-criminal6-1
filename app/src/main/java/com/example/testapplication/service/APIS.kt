package com.example.testapplication.service

import com.example.testapplication.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.http.Field
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


public interface APIS {
    @GET("checkExist.php")
    fun checkExist(
        @Query("id") param1: String? = null
    ): Call<UserModel>

    @FormUrlEncoded
    @POST("signup.php")
    fun signup(
        @Field("id") id: String? = null,
        @Field("password") password: String? = null,
        @Field("name") name: String? = null
    ): Call<UserSignupPostModel>

    @FormUrlEncoded
    @POST("signin.php")
    fun signin(
        @Field("id") id: String? = null,
        @Field("password") password: String? = null
    ): Call<UserSigninModel>

    @FormUrlEncoded
    @POST("createRoom.php")
    fun createRoom(
        @Field("createrUserId") createrUserId: String? = null,
        @Field("title") title: String? = null,
        @Field("region1") region1: String? = null,
        @Field("date") date: String? = null,
        @Field("genre") genre: String? = null,
        @Field("difficulty") difficulty: Int? = null,
        @Field("fear") fear: Int? = null,
        @Field("activity") activity: Int? = null,
        @Field("roomIntro") roomIntro: String? = null,
    ): Call<createRoomPostModel>

    @GET("getRoomList.php")
    fun getRoomList(
        @Query("id") param1: String? = null
    ): Call<getRoomListModel>

    @GET("getAllRoomInfo.php")
    fun getAllRoomInfo(
    ): Call<getAllRoomInfoModel>

    @GET("getRoomRecommendation.php")
    fun getRoomRecommendation(
        @Query("id") id: String? = null,
        @Query("roomID1") param1: Int? = null,
        @Query("roomID2") param2: Int? = null,
        @Query("roomID3") param3: Int? = null
    ): Call<getRoomRecommendationModel>

    @FormUrlEncoded
    @POST("addUserToChatRoom.php")
    fun addUserToChatRoom(
        @Field("id") id: String? = null,
        @Field("roomID") roomID: Int? = null
    ): Call<Void>

    @FormUrlEncoded
    @POST("postRecommendationList.php")
    fun postRecommendationList(
        @Field("id") id: String? = null,
        @Field("roomID1") roomID1: Int? = null,
        @Field("roomID2") roomID2: Int? = null,
        @Field("roomID3") roomID3: Int? = null
    ): Call<Void>

    @GET("getRecommendationList.php")
    fun getRecommendationList(
        @Query("id") id: String? = null
    ): Call<getRecommendationList>

    @GET("getAllChat.php")
    fun getAllChat(
        @Query("roomID") roomID: Int? = null
    ): Call<getAllChat>

    @FormUrlEncoded
    @POST("sendChat.php")
    fun sendChat(
        @Field("id") id: String? = null,
        @Field("content") content: String? = null,
        @Field("roomID") roomID: Int? = null,
        @Field("createdAt") createdAt: String? = null,
    ):Call<Void>



    companion object {
        private const val BASE_URL =
            "http://ec2-3-34-134-247.ap-northeast-2.compute.amazonaws.com/"

        fun create(): APIS {
            val gson: Gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APIS::class.java)
        }
    }
}