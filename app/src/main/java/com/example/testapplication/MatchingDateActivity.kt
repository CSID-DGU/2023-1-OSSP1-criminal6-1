package com.example.testapplication

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.testapplication.databinding.ActivityMatchingDateBinding
import java.util.*

class MatchingDateActivity : AppCompatActivity() {
    private lateinit var btn_next: Button
    private lateinit var btn_back: Button
    var startDateString = ""
    var endDateString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMatchingDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val area1 = intent.getStringExtra("area1")
        val area2 = intent.getStringExtra("area2")
        val area3 = intent.getStringExtra("area3")

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
                    startDateString = "$year/$month/$dayOfMonth"
                    Log.d("시작일: ", startDateString)
                    binding.tvStartdate.text = startDateString

                },
                year,
                month,
                day
            )
            startdate.datePicker.minDate = System.currentTimeMillis() - 1000;
            startdate.show()
        }


        binding.ibEnddate.setOnClickListener {
            val startDate = binding.tvStartdate.text.toString()
            val startDateParts = startDate.split("/")
            val startYear = startDateParts[0].toInt()
            val startMonth = startDateParts[1].toInt() - 1
            val startDay = startDateParts[2].toInt()


            val datepickercalendar = Calendar.getInstance()
            val year = datepickercalendar.get(Calendar.YEAR)
            val month = datepickercalendar.get(Calendar.MONTH)
            val day = datepickercalendar.get(Calendar.DAY_OF_MONTH)

            val enddate = DatePickerDialog(
                this,
                R.style.DialogDatePicker_Theme,
                { _, year, monthOfYear, dayOfMonth ->
//                  월이 0부터 시작하여 1을 더해주어야함
                    val month = monthOfYear + 1
//                   선택한 날짜의 요일을 구하기 위한 calendar
                    val calendar = Calendar.getInstance()
//                    선택한 날짜 세팅
                    calendar.set(year, monthOfYear, dayOfMonth)
                    //종료일 String 값
                    endDateString = "$year/$month/$dayOfMonth"
                    Log.d("종료일: ", endDateString)
                    binding.tvEnddate.text = endDateString
                },
                year,
                month,
                day
            )
            val startDateMillis = Calendar.getInstance().apply {
                set(startYear, startMonth, startDay, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis
            enddate.datePicker.minDate = startDateMillis
            enddate.show()
        }

        //다음으로 클릭
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, MatchingOptionActivity::class.java)
            intent.putExtra("startdate", startDateString)
            intent.putExtra("enddate", endDateString)
            intent.putExtra("area1", area1)
            intent.putExtra("area2", area2)
            intent.putExtra("area3", area3)
            startActivity(intent)
            finish()
        }


        binding.btnPrev.setOnClickListener {
            val intent = Intent(this, MatchingLocalActivity::class.java)
            startActivity(intent)
            finish()
        }
        /*
        //이전으로(상단) 클릭
        binding.btnBackTop.setOnClickListener {
            val intent = Intent(this, Matching_intro::class.java)
            startActivity(intent)
            finish()
        }
        //x표시 클릭(첫 화면으로 이동)
        binding.btnClose.setOnClickListener {
            val intent = Intent(this, Matching_intro::class.java)
            startActivity(intent)
            finish()
        }

         */
    }
}