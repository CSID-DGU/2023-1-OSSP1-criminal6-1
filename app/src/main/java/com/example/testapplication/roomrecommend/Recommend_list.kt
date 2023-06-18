package com.example.testapplication.roomrecommend

//import com.example.testapplication.roomrecommend.MyAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.ActivityRecommendListBinding
import com.example.testapplication.matching.MatchingFailActivity
import com.example.testapplication.model.response.getroomlistresponse
import retrofit2.Call
import retrofit2.Response


class Recommend_list : AppCompatActivity() {
//    private var _binding: ActivityRecommendListBinding? = null
//    private val binding: ActivityRecommendListBinding
//        get() = requireNotNull(_binding)


   // private lateinit var adapter: com.example.testapplication.roomrecommend.MyAdapter

    private val RoomlistService = CriminalServicePool.roomlistService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecommendListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //adapter = com.example.testapplication.roomrecommend.MyAdapter(roomList)
        //binding.recyclerView.adapter = adapter

        val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        RoomlistService.roomlist(
        ).enqueue(object : retrofit2.Callback<getroomlistresponse> {
            override fun onResponse(
                call: Call<getroomlistresponse>,
                response: Response<getroomlistresponse>
            ) {
                if (response.body()?.success == true) {
                    Log.d("getList", response.body().toString())
                    val roomListResponse = response.body()?.data

                    val roomid = response.body()!!.roomid

                    val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("roomid", roomid.toString())
                    editor.apply()

                    if (roomListResponse!!.isNotEmpty()) {
                        val adapter = MyAdapter()
                        binding.recyclerView.adapter = adapter
                        adapter.submitList(roomListResponse.toList())
                    }
                    else {
                        val intent = Intent(this@Recommend_list, MatchingFailActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }

            }

            override fun onFailure(call: Call<getroomlistresponse>, t: Throwable) {
                Log.d("fail", "왜 안오노")
            }

        })


    }
}