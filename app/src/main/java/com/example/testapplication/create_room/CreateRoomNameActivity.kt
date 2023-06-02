package com.example.testapplication.create_room

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.databinding.ActivityCreateRoomNameBinding

class CreateRoomNameActivity : AppCompatActivity() {

    private lateinit var Binding: ActivityCreateRoomNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityCreateRoomNameBinding.inflate(layoutInflater)
        setContentView(Binding.root)
        val DateString =intent.getStringExtra("date")
        val area1 = intent.getStringExtra("area1")
        val genre = intent.getStringExtra("genre")
        val diff = intent.getStringExtra("diff")
        val activity = intent.getStringExtra("activity")
        val fear = intent.getStringExtra("fear")

        val roomintro = Binding.roomIntroEdit.text


        Binding.btnNext.setOnClickListener {
            val intent = Intent(this, CreateTotalActivity::class.java)
            intent.putExtra("date", DateString)
            intent.putExtra("area1", area1)
            intent.putExtra("genre", genre)
            intent.putExtra("diff", diff)
            intent.putExtra("fear", fear)
            intent.putExtra("activity", activity)
            val roomtitle = Binding.roomTitleEdit.text.toString()
            intent.putExtra("roomtitle", roomtitle)
            val roomintro = Binding.roomIntroEdit.text.toString()
            intent.putExtra("roomintro", roomintro)
            startActivity(intent)
            finish()
        }
        //페이지 이동
        Binding.btnPrev.setOnClickListener {
            val intent2 = Intent(this, CreateRoomOptionActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }
}