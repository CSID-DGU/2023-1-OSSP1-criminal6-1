package com.example.testapplication.matching

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.MainActivity
import com.example.testapplication.databinding.ActivityMatchingLocalBinding


class MatchingLocalActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMatchingLocalBinding

    var area1 = ""
    var area2 = ""
    var area3 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMatchingLocalBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        //setContentView(R.layout.activity_create_room_local)
//스피너 어댑터
        //var sData = resources.getStringArray(R.array.testarray)
        var sData = listOf("상관없음", "강남", "건대", "노량진","대구", "대전", "부천", "부평", "분당", "상수",
            "서면", "서울대입구", "성수", "수원", "수유", "신림", "신촌", "인천", "잠실", "혜화", "홍대")
        var adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, sData)
        val spinCity1 = mBinding.spinnerCity1
        spinCity1.adapter = adapter
        spinCity1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //position은 선택한 아이템의 위치를 넘겨주는 인자입니다.
                //mBinding.text = sData.get(position)
                area1 = sData.get(position)
                Log.d("지역1",  sData.get(position))
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        val spinCity2 = mBinding.spinnerCity2
        spinCity2.adapter = adapter
        spinCity2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //position은 선택한 아이템의 위치를 넘겨주는 인자입니다.
                //mBinding.text = sData.get(position)
                area2 = sData.get(position)
                Log.d("지역2",  sData.get(position))
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val spinCity3 = mBinding.spinnerCity3
        spinCity3.adapter = adapter
        spinCity3.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //position은 선택한 아이템의 위치를 넘겨주는 인자입니다.
                //mBinding.text = sData.get(position)
                area3 = sData.get(position)
                Log.d("지역3",  sData.get(position))
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        //페이지 이동
//        binding.btnNext.setOnClickListener{
//            val intent= Intent(this, CreateRoomLocalActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
        mBinding.btnNext.setOnClickListener {
            val intent = Intent(this, MatchingDateActivity::class.java)
            intent.putExtra("area1", area1)
            intent.putExtra("area2", area2)
            intent.putExtra("area3", area3)
            startActivity(intent)
            finish()
        }
        mBinding.btnPrev.isEnabled = false
        mBinding.btnBack.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
            finish()
        }

    }
}