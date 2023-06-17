package com.example.testapplication.service

import com.example.testapplication.model.request.signuprequest
import com.example.testapplication.model.response.signupresponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignService {

    @POST("signup/")
    fun signup(
        @Body request: signuprequest
    ): Call<signupresponse>


//    @POST("sign-in")
//    fun signin(
//        @Body request: RequestLogin
//    ): Call<ResponseLogin>
}