package com.example.testapplication.matching

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.testapplication.roomrecommend.Recommend_list
import com.example.testapplication.databinding.ActivityMatchingTotalBinding
import com.example.testapplication.model.request.searchroomrequest
import com.example.testapplication.model.response.searchroomresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MatchingTotalActivity : AppCompatActivity() {
    private val SearchRoomService = CriminalServicePool.searchroomService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMatchingTotalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val area1 = intent.getStringExtra("area1")
        binding.tvArea1.text = area1
        val area2 = intent.getStringExtra("area2")
        binding.tvArea2.text = area2
        val area3 = intent.getStringExtra("area3")
        binding.tvArea3.text = area3
        var startdate = intent.getStringExtra("startdate")
        binding.tvDateStart.text = startdate

        startdate = startdate.toString().replace("/", "")

        var enddate = intent.getStringExtra("enddate")
        binding.tvDateEnd.text = enddate

        enddate = enddate.toString().replace("/", "")
        val genre = intent.getStringExtra("genre")
        binding.tvGenre.text = genre
        var diff = intent.getStringExtra("diff")
        binding.tvLevel.text = diff

        var activity = intent.getStringExtra("activity")
        binding.tvActivity.text = activity


        var fear = intent.getStringExtra("fear")
        binding.tvFear.text = fear


        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MatchingOptionActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCreate.setOnClickListener {
            SearchRoomService.roomsearch(
                searchroomrequest(
                    activity.toString(),
                    area1.toString(),
                    area2.toString(),
                    area3.toString(),
                    diff.toString(),
                    enddate,
                    fear.toString(),
                    genre.toString(),
                    startdate
                )
            ).enqueue(object : Callback<searchroomresponse> {
                   @RequiresApi(Build.VERSION_CODES.N)
                    override fun onResponse(
                        call: Call<searchroomresponse>,
                        response: Response<searchroomresponse>
                    ) {
                         if (response.isSuccessful){
                             Toast.makeText(applicationContext, "방 매칭을 시작합니다.", Toast.LENGTH_SHORT)
                                 .show()
                             val intent = Intent(this@MatchingTotalActivity, Recommend_list::class.java)
                             startActivity(intent)
                             finish()
                         }
                    }

                    override fun onFailure(call: Call<searchroomresponse>, t: Throwable) {
                        Log.d("failGetAllRoomInfo", "failll")
                    }

                })
            val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)


        }
    }


}