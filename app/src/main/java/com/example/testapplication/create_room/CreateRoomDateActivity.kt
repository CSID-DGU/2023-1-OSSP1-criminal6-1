package com.example.testapplication.create_room

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.matching.MatchingFragment
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityCreateRoomDateBinding
import java.util.*

class CreateRoomDateActivity : AppCompatActivity() {
    private lateinit var btn_next: Button
    private lateinit var btn_back: Button
    var DateString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateRoomDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val area1 = intent.getStringExtra("area1")


        //시작일
        binding.ibStartdate.setOnClickListener {
            val datepickercalendar = Calendar.getInstance()
            val year = datepickercalendar.get(Calendar.YEAR)
            val month = datepickercalendar.get(Calendar.MONTH)
            val day = datepickercalendar.get(Calendar.DAY_OF_MONTH)

            val startdate = DatePickerDialog(
                this,
                R.style.DialogDatePicker_Theme,
                { _, year, monthOfYear, dayOfMonth ->
//                  월이 0부터 시작하여 1을 더해주어야함
                    val month = monthOfYear + 1
//                   선택한 날짜의 요일을 구하기 위한 calendar
                    val calendar = Calendar.getInstance()
//                    선택한 날짜 세팅
                    calendar.set(year, monthOfYear, dayOfMonth)
                    //시작일 String 값
                    DateString = "$year/$month/$dayOfMonth"
                    Log.d("날짜: ", DateString)
                    binding.tvDate.text = DateString

                },
                year,
                month,
                day
            )
            startdate.datePicker.minDate = System.currentTimeMillis() - 1000;
            startdate.show()
        }


        //다음으로 클릭
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, CreateRoomOptionActivity::class.java)
            intent.putExtra("date", DateString)
            intent.putExtra("area1", area1)
            startActivity(intent)
            finish()
        }

        //이전으로(하단) 클릭
        binding.btnPrev.setOnClickListener {
            val intent = Intent(this, CreateRoomLocalActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnBack.setOnClickListener {
            val intent2 = Intent(this, MatchingFragment::class.java)
            startActivity(intent2)
            finish()
        }
        /*
        //x표시 클릭(첫 화면으로 이동)
        binding.btnClose.setOnClickListener {
            val intent = Intent(this, Matching_intro::class.java)
            startActivity(intent)
            finish()
        }

         */
    }
}