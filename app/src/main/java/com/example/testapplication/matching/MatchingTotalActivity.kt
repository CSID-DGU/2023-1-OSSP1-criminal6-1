package com.example.testapplication.matching

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.testapplication.Recommend_list
import com.example.testapplication.databinding.ActivityMatchingTotalBinding
import com.example.testapplication.getAllRoomInfoModel
import com.example.testapplication.service.APIS
import com.google.gson.GsonBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MatchingTotalActivity : AppCompatActivity() {
    private val api = APIS.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMatchingTotalBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val area1 = intent.getStringExtra("area1")
        binding.tvArea1.text = area1
        val area2 = intent.getStringExtra("area2")
        binding.tvArea2.text = area2
        val area3 = intent.getStringExtra("area3")
        binding.tvArea3.text = area3
        var startdate = intent.getStringExtra("startdate")
        binding.tvDateStart.text = startdate

        startdate = startdate.toString().replace("/", "")

        var enddate = intent.getStringExtra("enddate")
        binding.tvDateEnd.text = enddate

        enddate = enddate.toString().replace("/", "")
        val genre = intent.getStringExtra("genre")
        binding.tvGenre.text = genre
        var diff = intent.getStringExtra("diff")
        binding.tvLevel.text = diff

        if (diff.toString() == "상")
            diff = "2"
        else if (diff.toString() == "중")
            diff = "1"
        else if (diff.toString() == "하")
            diff = "0"
        else if (diff.toString() == "")
            diff = "11"

        var activity = intent.getStringExtra("activity")
        binding.tvActivity.text = activity

        if (activity.toString() == "상")
            activity = "2"
        else if (activity.toString() == "중")
            activity = "1"
        else if (activity.toString() == "하")
            activity = "0"
        else if (activity.toString() == "")
            activity = "11"

        var fear = intent.getStringExtra("fear")
        binding.tvFear.text = fear


        if (fear.toString() == "상")
            fear = "2"
        else if (fear.toString() == "중")
            fear = "1"
        else if (fear.toString() == "하")
            fear = "0"
        else if (fear.toString() == "")
            fear = "11"

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MatchingOptionActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnCreate.setOnClickListener {
            val useroption =
                arrayOf(area1, area2, area3, startdate, enddate, genre, diff, fear, activity)
            Log.d("상중하", useroption[3].toString())
            api.getAllRoomInfo()
                .enqueue(object : Callback<getAllRoomInfoModel> {
                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun onResponse(
                        call: Call<getAllRoomInfoModel>,
                        response: Response<getAllRoomInfoModel>
                    ) {
                        var i = 0
                        val text =
                            "{\"activity\":1,\"date\":\"2022.12.20\",\"difficulty\":1,\"fear\":0,\"genre\":\"판타지\",\"region1\":\"강남\",\"region2\":\"건대\",\"region3\":\"상수\",\"roomID\":24,\"title\":\"동국 탈출 넘버원\"}"

                        Log.d("response11", response.body().toString())
                        var gson = GsonBuilder().create()
                        var sj = gson.toJson(response.body())
                        Log.d("json", sj)

                        val jsonArray =
                            JSONObject(sj).optJSONArray("roomInfoList")

                        val array = Array(jsonArray.length(), { Array(9, { "" }) })
                        val countarray = Array(jsonArray.length(), {FloatArray(2, {0.0f})})
                        while (i < jsonArray.length()) {
                            val JsonObject = jsonArray.getJSONObject(i)
                            val roomID = JsonObject.getString("roomID").toString()
                            val region1 = JsonObject.getString("region1")
                            val region2 = JsonObject.getString("region2")
                            val region3 = JsonObject.getString("region3")
                            val date = JsonObject.getString("date").toString().replace(".", "")
                            val genre = JsonObject.getString("genre")
                            val difficulty = JsonObject.getString("difficulty").toString()
                            val fear = JsonObject.getString("fear").toString()
                            val activity = JsonObject.getString("activity").toString()

                            array[i][0] = roomID
                            array[i][1] = region1
                            array[i][2] = region2
                            array[i][3] = region3
                            array[i][4] = date
                            array[i][5] = genre
                            array[i][6] = difficulty
                            array[i][7] = fear
                            array[i][8] = activity
                            i++
                        }


                        val filteredRooms = mutableListOf<Array<String>>()

                        var j = 0;
                        while (j < jsonArray.length()) {
                            var count = 0.0f
                            var recount = 0.0f

                            for(a: Int in 0..2) {
                                for (b: Int in 0..2) {
                                    if (array[j][a + 1] == useroption[b])
                                        recount++
                                }
                            }
                            count += (recount / 3)

                            val roomDate = Integer.parseInt(array[j][4])
                            if (roomDate >= Integer.parseInt(useroption[3].toString())
                                && roomDate <= Integer.parseInt(useroption[4].toString())
                            ) {
                                // 날짜가 포함되는 방을 리스트에 추가
                                filteredRooms.add(array[j])
                            }


                            if (Integer.parseInt(array[j][4]) >= Integer.parseInt(useroption[3].toString())
                                && Integer.parseInt(array[j][4]) <= Integer.parseInt(useroption[4].toString())
                            )
                                count++
                            if (array[j][5] == useroption[5])
                                count++
                            if (array[j][6] == useroption[6].toString())
                                count++
                            if (array[j][7] == useroption[7].toString())
                                count++
                            if (array[j][8] == useroption[8].toString())
                                count++
                            countarray[j][1] = count
                            countarray[j][0] = array[j][0].toFloat()
                            Log.d("count", j.toString() + " " + count.toString())
                            j++
                        }

                        for(c: Int in 0..jsonArray.length()-1) {
                            Log.d("ddd,ddd", countarray[c][0].toString())
                            Log.d("ddd,ddd", countarray[c][1].toString())

                        }

                        for(c: Int in 0..jsonArray.length() -1 ){
                            var temp_id = countarray[c][0]
                            var temp = countarray[c][1]
                            var min = 10.0f
                            var minidx = 0
                            for (d: Int in c .. jsonArray.length() - 1){
                                if(countarray[d][1] < min ) {
                                    min = countarray[d][1]
                                    minidx = d
                                }
                            }
                            Log.d("d", minidx.toString())
                            countarray[c][1] = countarray[minidx][1]
                            countarray[c][0] = countarray[minidx][0]
                            countarray[minidx][1] = temp
                            countarray[minidx][0] = temp_id

                        }

                        for(c: Int in 0..jsonArray.length()-1) {
                            Log.d("ccc,ccc", countarray[c][0].toString())
                            Log.d("ccc,ccc", countarray[c][1].toString())

                        }

                        val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)
                        //countarray.sort()
                        //Arrays.sort(countarray, Comparator.comparingInt())
                        api.postRecommendationList(
                            preferences.getString("userId", ""),
                            countarray[jsonArray.length() - 1][0].toInt(),
                            countarray[jsonArray.length() - 2][0].toInt(),
                            countarray[jsonArray.length() - 3][0].toInt()
                        ).enqueue(object : retrofit2.Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                                Log.d("roomPostSuccess", "asdfasdfasdf")
                            }
                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Log.d("roomPostFail", "asdfasdfasdfa")
                            }
                        })

                    }

                    override fun onFailure(call: Call<getAllRoomInfoModel>, t: Throwable) {
                        Log.d("failGetAllRoomInfo", "failll")
                    }

                })
            val preferences = getSharedPreferences("userInfo", MODE_PRIVATE)

            val intent = Intent(this, Recommend_list::class.java)
            startActivity(intent)
            finish()
        }
    }


}