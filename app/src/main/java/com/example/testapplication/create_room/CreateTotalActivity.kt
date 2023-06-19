package com.example.testapplication.create_room

import CriminalServicePool
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.MainActivity
import com.example.testapplication.databinding.ActivityCreateTotalBinding
import com.example.testapplication.model.request.creatroomrequest
import com.example.testapplication.model.response.createroomresponse
import retrofit2.Call
import retrofit2.Response

class CreateTotalActivity : AppCompatActivity() {
    private val CreateRoomService = CriminalServicePool.createroomService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateTotalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userid = sharedPreferences.getString("userid", "")

        val title = intent.getStringExtra("roomtitle")
        binding.tvRoomtitle.text = title
        val region = intent.getStringExtra("area1")
        binding.tvArea1.text = region
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
        for (i in 0 until 3) {
            dateString += dateArray[i]
            dateString += "."
        }

        dateString = dateString.substring(0, dateString.length - 1)

        binding.btnCreate.setOnClickListener {
            CreateRoomService.roomcreate(
                creatroomrequest(
                    userid.toString(),
                    activity.toString(),
                    dateString,
                    difficulty.toString(),
                    fear.toString(),
                    genre.toString(),
                    region.toString(),
                    roomIntro.toString(),
                    title.toString()
                )
            ).enqueue(object : retrofit2.Callback<createroomresponse> {
                override fun onResponse(
                    call: Call<createroomresponse>,
                    response: Response<createroomresponse>
                ) {
                    Log.d("aaa", response.body().toString())
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "방 생성이 완료되었습니다.", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@CreateTotalActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onFailure(call: Call<createroomresponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "방 생성에 실패하였습니다.", Toast.LENGTH_SHORT)
                        .show()
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