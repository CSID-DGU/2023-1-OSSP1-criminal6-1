package com.example.testapplication.matching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Matching_intro : AppCompatActivity() {
    private lateinit var btn_intro: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout)

        //btn_intro=findViewById(R.id.btn_intro)

        btn_intro.setOnClickListener{
            val intent= Intent(this, Matching_intro::class.java)
            startActivity(intent)
            finish()
        }
    }
}