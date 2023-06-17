package com.example.testapplication.sign

import CriminalServicePool
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.databinding.ActivityRegisterBinding
import com.example.testapplication.model.request.signuprequest
import com.example.testapplication.model.response.signupresponse
import retrofit2.Call
import retrofit2.Callback

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val SignupService = CriminalServicePool.signupService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Name = binding.Name
        val Id = binding.signupId
        val Password = binding.pwd
        val Password_check = binding.pwdCheck

        //var check = false
        binding.btnBack.setOnClickListener {
            val intent2 = Intent(this, LoginActivity::class.java)
            startActivity(intent2)
            finish()
        }
        binding.registerBtn.setOnClickListener {

            //이메일이 비어있을 때 2
            if (TextUtils.isEmpty(Id.text.toString()))
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
                    Log.d("signupbutton", "asdf")
                    SignupService.signup(
                        signuprequest(
                            Id.text.toString(),
                            Password.text.toString(),
                            Name.text.toString()
                        )
                    ).enqueue(object : Callback<signupresponse> {
                        override fun onResponse(
                            call: Call<signupresponse>,
                            response: retrofit2.Response<signupresponse>
                        ) {
                            //Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            finish()
                        }

                        override fun onFailure(call: Call<signupresponse>, t: Throwable) {
                            //Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                            Log.d("signupfail", t.toString())
                        }

                    })

            }
        }

    }
}