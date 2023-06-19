package com.example.testapplication.chat

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.testapplication.*
import com.example.testapplication.databinding.ActivityChatBinding
import com.example.testapplication.service.APIS
import retrofit2.Call
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatActivity : AppCompatActivity() {

    lateinit var multiAdapter: chatAdapter
    val datas = mutableListOf<chatData>()

    private val api = APIS.create()
    private lateinit var Binding: ActivityChatBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        multiAdapter = chatAdapter(this)
        Binding.chatRecyclerView.adapter = multiAdapter

        val roomId = intent.getStringExtra("roomID")
        val roomTitle = intent.getStringExtra("roomTitle")

    kotlin.concurrent.timer(period = 100, initialDelay = 0) {
        api.getAllChat(roomId?.toInt()).enqueue(object : retrofit2.Callback<getAllChat>{
            override fun onResponse(call: Call<getAllChat>, response: Response<getAllChat>) {
                Log.d("res", response.body().toString())
                var chatArray = response.body().toString().replace("getAllChat(result=", "")
                chatArray = chatArray.replace(")", "")
                var chatInfo = chatArray.split("/")
                Log.d("chatInfo", chatInfo[0].toString())
                val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
                val userId = preferences.getString("userId", "")
                datas.clear()
                datas.apply {
                    for(i: Int in 0..chatInfo.size-2) {
                        var chat = chatInfo[i].split(",")
                        if(userId == chat[0]){
                            add(chatData(name = chat[0], msg = chat[1], time = chat[2], multi_type2))
                        }
                        else{
                            add(chatData(name = chat[0], msg = chat[1], time = chat[2], multi_type1))
                        }
                        multiAdapter.datas = datas
                        multiAdapter.notifyDataSetChanged()

                    }
                }


            }

            override fun onFailure(call: Call<getAllChat>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

        Binding.textTitle.text = roomTitle

        Binding.massageSend.setOnClickListener {
            val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
            Log.d("datetime", Binding.chatInput.text.toString())
            api.sendChat(
                preferences.getString("userId", ""),
                Binding.chatInput.text.toString(),
                roomId?.toInt(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString()
            ).enqueue(object : retrofit2.Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Binding.chatInput.setText("")
                    Log.d(
                        "datetime",
                        LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                            .toString()

                    )
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("chatFail", "chaasdf")
                }

            })
            }
        Binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

           //val intent2 = Intent(this, ChatFragment::class.java)
            //startActivity(intent2)
            //finish()
        }
    }
}