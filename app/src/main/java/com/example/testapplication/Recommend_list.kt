package com.example.testapplication

import MyAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.ActivityRecommendListBinding
import com.example.testapplication.service.APIS
import retrofit2.Call
import retrofit2.Response


class Recommend_list : AppCompatActivity() {
    private val api = APIS.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecommendListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewItems: List<getRoomRecommendationModel.recommendationRoomInfo> = emptyList()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(recyclerViewItems)
        binding.recyclerView.adapter = adapter

        val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        api.getRecommendationList(
            preferences.getString("userId", "")
        ).enqueue(object : retrofit2.Callback<getRecommendationList> {
            override fun onResponse(
                call: Call<getRecommendationList>,
                response: Response<getRecommendationList>
            ) {
                Log.d("getList", response.body().toString())


                var resultString = response.body().toString().replace("getRecommendationList(result=", "")
                resultString = resultString.replace(")", "")
                var resultArray = resultString.split(",")
                Log.d("getList", resultArray[0])
                Log.d("getList", resultArray[1])
                Log.d("getList", resultArray[2])


                if (resultArray.size >= 3) {
                    api.getRoomRecommendation(
                        preferences.getString("userId", ""),
                        Integer.parseInt(resultArray[0]),
                        Integer.parseInt(resultArray[1]),
                        Integer.parseInt(resultArray[2])
                    ).enqueue(object : retrofit2.Callback<getRoomRecommendationModel> {
                        override fun onResponse(
                            call: Call<getRoomRecommendationModel>,
                            response: Response<getRoomRecommendationModel>
                        ) {
                            Log.d("정보", "${response.body()}")
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    adapter.setRepoList(it.roomInfoList)
                                }
                            }
                        }

                        override fun onFailure(call: Call<getRoomRecommendationModel>, t: Throwable) {
                            Log.d("오류", t.toString())
                        }
                    })
                } else {
                    Log.d("오류", "resultArray의 크기가 3 이하입니다.")
                }

            }

            override fun onFailure(call: Call<getRecommendationList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }
}