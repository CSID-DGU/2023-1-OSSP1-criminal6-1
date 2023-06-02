package com.example.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.testapplication.chat.ChatFragment
import com.example.testapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val frame: FragmentContainerView by lazy { // activity_main의 화면 부분
        findViewById(R.id.main_container)
    }

    private val bottomNagivationView: BottomNavigationView by lazy { // 하단 네비게이션 바
        findViewById(R.id.bnv_main)
    }
    private lateinit var binding1: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding1 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding1.root)

        if (frame == null) {
            supportFragmentManager.beginTransaction()
                .add(frame.id, ChatFragment())
                .commit()
        }
        replaceFragment(MatchingFragment())

        bottomNagivationView.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {
                R.id.nav_category -> {
                    replaceFragment(CategoryFragment())
                    true
                }
                R.id.nav_matching -> {
                    replaceFragment(MatchingFragment())
                    true
                }
                R.id.nav_chat -> {
                    replaceFragment(ChatFragment())
                    true
                }
                R.id.nav_mypage -> {
                    replaceFragment(MyPageFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
    }
}