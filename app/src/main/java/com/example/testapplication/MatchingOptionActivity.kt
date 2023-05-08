package com.example.testapplication

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.databinding.ActivityCreateRoomOptionBinding
import com.example.testapplication.databinding.ActivityMatchingOptionBinding


class MatchingOptionActivity : AppCompatActivity() {
    private lateinit var Binding: ActivityMatchingOptionBinding
    var genre = ""
    var diff = ""
    var fear= ""
    var activity = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding = ActivityMatchingOptionBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        val startDateString = intent.getStringExtra("startdate")
        val endDateString =intent.getStringExtra("enddate")
        val area1 = intent.getStringExtra("area1")
        val area2 = intent.getStringExtra("area2")
        val area3 = intent.getStringExtra("area3")

        //스피너 어댑터
        //var sData = resources.getStringArray(R.array.testarray)
        var sData = listOf("상관없음","판타지","19금","SF", "감성", "공포", "기타", "모험", "미션", "스토리",
            "추리", "코믹")
        var adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1,sData)
        val spinThema =Binding.spinnerThema
        spinThema.adapter=adapter
        spinThema.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //position은 선택한 아이템의 위치를 넘겨주는 인자입니다.
                //mBinding.text = sData.get(position)
                genre = sData.get(position)
                Log.d("장르:", sData.get(position))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
//        val btnDifficultyHigh =Binding.difficultyHigh
//        val btnDifficultyMiddle =Binding.difficultyMiddle
//        val btnDifficultyLow =Binding.difficultyLow
//        val btnScaryHigh =Binding.scaryHigh
//        val btnScaryMiddle =Binding.scaryMiddle
//        val btnScaryLow =Binding.scaryLow

        //난이도 선택 버튼
        Binding.difficultyHigh.setOnClickListener{
            if(diff!="상") {
                if (diff == "중")
                    Binding.difficultyMiddle.isSelected = Binding.difficultyMiddle.isSelected != true
                if (diff == "하")
                    Binding.difficultyLow.isSelected = Binding.difficultyLow.isSelected != true
                diff = "상"
            }
            else diff = ""
            Binding.difficultyHigh.isSelected = Binding.difficultyHigh.isSelected != true
        }
        Binding.difficultyMiddle.setOnClickListener{
            if(diff!="중") {
                if (diff == "상")
                    Binding.difficultyHigh.isSelected = Binding.difficultyHigh.isSelected != true
                if (diff == "하")
                    Binding.difficultyLow.isSelected = Binding.difficultyLow.isSelected != true
                diff = "중"
            }
            else diff = ""
            Binding.difficultyMiddle.isSelected = Binding.difficultyMiddle.isSelected != true
        }
        Binding.difficultyLow.setOnClickListener{
            if(diff!="하") {
                if (diff == "상")
                    Binding.difficultyHigh.isSelected = Binding.difficultyHigh.isSelected != true
                if (diff == "중")
                    Binding.difficultyMiddle.isSelected = Binding.difficultyMiddle.isSelected != true
                diff = "하"
            }
            else diff = ""
            Binding.difficultyLow.isSelected = Binding.difficultyLow.isSelected != true
        }

        //공포도 선택
        Binding.fearHigh.setOnClickListener{
            if(fear!="상") {
                if (fear == "중")
                    Binding.fearMiddle.isSelected = Binding.fearMiddle.isSelected != true
                if (fear == "하")
                    Binding.fearLow.isSelected = Binding.fearLow.isSelected != true
                fear = "상"
            }
            else fear = ""
            Binding.fearHigh.isSelected = Binding.fearHigh.isSelected != true
        }
        Binding.fearMiddle.setOnClickListener{
            if(fear!="중") {
                if (fear == "상")
                    Binding.fearHigh.isSelected = Binding.fearHigh.isSelected != true
                if (fear == "하")
                    Binding.fearLow.isSelected = Binding.fearLow.isSelected != true
                fear = "중"
            }
            else fear = ""
            Binding.fearMiddle.isSelected = Binding.fearMiddle.isSelected != true
        }
        Binding.fearLow.setOnClickListener{
            if(fear!="하") {
                if (fear == "상")
                    Binding.fearHigh.isSelected = Binding.fearHigh.isSelected != true
                if (fear == "중")
                    Binding.fearMiddle.isSelected = Binding.fearMiddle.isSelected != true
                fear = "하"
            }
            else fear = ""
            Binding.fearLow.isSelected = Binding.fearLow.isSelected != true
        }

        //활동성 선택
        Binding.activityHigh.setOnClickListener{
            if(activity!="상") {
                if (activity == "하")
                    Binding.activityLow.isSelected = Binding.activityLow.isSelected != true
                if (activity == "중")
                    Binding.activityMiddle.isSelected = Binding.activityMiddle.isSelected != true
                activity = "상"
            }
            else activity = ""
            Binding.activityHigh.isSelected = Binding.activityHigh.isSelected!=true
        }
        Binding.activityMiddle.setOnClickListener{
            if(activity!="중") {
                if (activity == "상")
                    Binding.activityHigh.isSelected = Binding.activityHigh.isSelected != true
                if (activity == "하")
                    Binding.activityLow.isSelected = Binding.activityLow.isSelected != true
                activity = "중"
            }
            else activity = ""
            Binding.activityMiddle.isSelected = Binding.activityMiddle.isSelected!=true
        }
        Binding.activityLow.setOnClickListener{
            if(activity!="하") {
                if (activity == "상")
                    Binding.activityHigh.isSelected = Binding.activityHigh.isSelected != true
                if (activity == "중")
                    Binding.activityMiddle.isSelected = Binding.activityMiddle.isSelected != true
                activity = "하"
            }
            else activity = ""
            Binding.activityLow.isSelected = Binding.activityLow.isSelected!=true
        }


        //페이지 이동
        Binding.btnNext.setOnClickListener{
            val intent = Intent(this, MatchingTotalActivity::class.java)
            intent.putExtra("startdate", startDateString)
            intent.putExtra("enddate", endDateString)
            intent.putExtra("area1", area1)
            intent.putExtra("area2", area2)
            intent.putExtra("area3", area3)
            intent.putExtra("genre", genre)
            intent.putExtra("diff", diff)
            intent.putExtra("fear", fear)
            intent.putExtra("activity", activity)
            startActivity(intent)
            finish()
        }
        //페이지 이동
        Binding.btnPrev.setOnClickListener{
            val intent2= Intent(this, MatchingDateActivity::class.java)
            startActivity(intent2)
            finish()
        }
        Binding.btnBack.setOnClickListener{
            val intent2= Intent(this, MainActivity::class.java)
            startActivity(intent2)
            finish()
        }

//        var fragN : Int = 0
//        setFrag(0)
//
//        binding.btnNext.setOnClickListener{
//            if(fragN<1) {
//                fragN += 1
//                setFrag(fragN)
//            }
//        }
//        binding.btnPast.setOnClickListener{
//            if(fragN>0) {
//                fragN -= 1
//                setFrag(fragN)
//            }
//        }
    }


//    private fun setFrag(fragNum : Int) {
//        val ft = supportFragmentManager.beginTransaction()
//        when(fragNum)
//        {
//            0->{
//                ft.replace(R.id.frame_createroom, FragmentSelectLocal()).commit()
//            }
//            1->{
//                ft.replace(R.id.frame_createroom, FragmentSelectOption()).commit()
//            }
//        }
//    }
}