package com.example.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20220311.adapters.MainViewPager2Adapter
import com.example.keepthetime_20220311.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        binding.mainViewPager2.adapter = MainViewPager2Adapter( this ) // 변수 : Activity => 객체 : Context로 대입불가.

    }
}