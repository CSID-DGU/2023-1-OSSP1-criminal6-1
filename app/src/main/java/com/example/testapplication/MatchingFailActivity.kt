package com.example.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapplication.create_room.CreateRoomDateActivity
import com.example.testapplication.create_room.CreateRoomLocalActivity
import com.example.testapplication.databinding.ActivityMatchingFailBinding

class MatchingFailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMatchingFailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnMakeroom.setOnClickListener{
            val intent = Intent(this, CreateRoomLocalActivity::class.java)
            startActivity(intent)
        }


        binding.btnGohome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }



}