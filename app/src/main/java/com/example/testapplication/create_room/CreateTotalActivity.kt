package com.example.testapplication.create_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testapplication.MainActivity
import com.example.testapplication.createRoomPostModel
import com.example.testapplication.databinding.ActivityCreateTotalBinding
import com.example.testapplication.service.APIS
import retrofit2.Call
import retrofit2.Response

class CreateTotalActivity : AppCompatActivity() {
    private val api = APIS.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateTotalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val createrUserId = preferences.getString("userId", "")
        val roomtitle = intent.getStringExtra("roomtitle")
        binding.tvRoomtitle.text = roomtitle
        val region1 = intent.getStringExtra("area1")
        binding.tvArea1.text = region1
        val date = intent.getStringExtra("date")
        binding.tvDate.text = date
        val genre = intent.getStringExtra("genre")
        binding.tvGenre.text = genre
        val difficulty = intent.getStringExtra("diff")
        binding.tvLevel.text = difficulty
        val fear = intent.getStringExtra("fear")
        binding.tvFear.text = fear
        val activity = intent.getStringExtra("activity")
        binding.tvActivity.text = activity
        val roomIntro = intent.getStringExtra("roomintro")
        binding.tvDes.text = roomIntro


        val dateArray = date.toString().split("/")
        var dateString = ""
        for(i in 0 until 3) {
            dateString += dateArray[i]
            dateString += "."
        }
        dateString = dateString.toString().substring(0, dateString.toString().length - 1)

        var difficultyNum = -1
        if(difficulty.toString() == "상")
            difficultyNum = 2
        else if(difficulty.toString() == "중")
            difficultyNum = 1
        else if (difficulty.toString() == "하")
            difficultyNum = 0

        var fearNum = -1
        if(fear.toString() == "상")
            fearNum = 2
        else if(fear.toString() == "중")
            fearNum = 1
        else if (fear.toString() == "하")
            fearNum = 0

        var activityNum = -1
        if(activity.toString() == "상")
            activityNum = 2
        else if(activity.toString() == "중")
            activityNum = 1
        else if (activity.toString() == "하")
            activityNum = 0

        binding.btnCreate.setOnClickListener {
            api.createRoom(
                createrUserId,
                roomtitle.toString(),
                region1.toString(),
                dateString,
                genre.toString(),
                difficultyNum,
                fearNum,
                activityNum,
                roomIntro.toString()
            ).enqueue(object : retrofit2.Callback<createRoomPostModel> {
                override fun onResponse(
                    call: Call<createRoomPostModel>,
                    response: Response<createRoomPostModel>
                ) {
                    Log.d("createRoom", "asdfasdf")
                    if(response.body()?.result.toString() == "createRoomSuccess") {
                        Log.d("createRoom", createrUserId.toString())
                        Toast.makeText(applicationContext, response.body()?.result.toString(), Toast.LENGTH_SHORT).show()

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Log.d("aassddff", "aassddff")
                        Toast.makeText(applicationContext, response.body()?.result.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<createRoomPostModel>, t: Throwable) {
                    Log.d("createRoomFail", "createRoomFail")
                    Log.d("createRoomFail", t.toString())
                }

            })
        }
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, CreateRoomOptionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}