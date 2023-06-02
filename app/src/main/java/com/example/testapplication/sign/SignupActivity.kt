package com.example.testapplication.sign

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.testapplication.UserModel
import com.example.testapplication.UserSignupPostModel
import com.example.testapplication.databinding.ActivityRegisterBinding
import com.example.testapplication.service.APIS
import retrofit2.Call
import retrofit2.Callback

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val api = APIS.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Name = binding.Name
        val Email = binding.email
        val Password = binding.pwd
        val Password_check = binding.pwdCheck

        var isDuplicated = true //중복확인, 초기값 true
        //var check = false
        binding.btnBack.setOnClickListener {
            val intent2 = Intent(this, LoginActivity::class.java)
            startActivity(intent2)
            finish()
        }
        binding.registerBtn.setOnClickListener {

            //이메일이 비어있을 때 2
            if (TextUtils.isEmpty(Email.text.toString()))
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()

            //이름이 비어있을 때 3
            else if (TextUtils.isEmpty(Name.text.toString()))
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()

            //비밀번호가 같지 않을 때 4
            else if (!Password.text.toString().equals(Password_check.text.toString()))
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()

            //비밀번호 확인이 비어있을 때 5
            else if (TextUtils.isEmpty(Password_check.text.toString()))
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()

            //비밀번호가 비어있을 때
            else if (TextUtils.isEmpty(Password.text.toString()))
                Toast.makeText(this, "비밀번호 확인을 입력해주세요", Toast.LENGTH_SHORT).show()
            else {
                if (!isDuplicated) {
                    Log.d("signupbutton", "asdf")
                    api.signup(
                        Email.text.toString(),
                        Password.text.toString(),
                        Name.text.toString()
                    ).enqueue(object : Callback<UserSignupPostModel> {
                        override fun onResponse(
                            call: Call<UserSignupPostModel>,
                            response: retrofit2.Response<UserSignupPostModel>
                        ) {
                            Log.d("signupsuccess", response.body()?.result.toString())
                            //Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            finish()
                        }

                        override fun onFailure(call: Call<UserSignupPostModel>, t: Throwable) {
                            //Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                            Log.d("signupfail", t.toString())
                        }

                    })
                } else {
                    Toast.makeText(this, "아이디 중복확인이 필요합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

            binding.submitBtn.setOnClickListener {
                api.checkExist(Email.text.toString()).enqueue(object : Callback<UserModel> {

                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        Log.d("asdf", t.toString())
                    }

                    override fun onResponse(
                        call: Call<UserModel>,
                        response: retrofit2.Response<UserModel>
                    ) {
                        Log.d("한글", response.body()?.result.toString())
                        if (response.body()?.result.toString().equals("AVAILABLE")){
                            Toast.makeText(applicationContext, "생성 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                            isDuplicated = false
                        }
                        else{
                            Toast.makeText(applicationContext, "생성 불가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
    }
}