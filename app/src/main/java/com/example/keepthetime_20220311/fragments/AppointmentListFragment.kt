package com.example.keepthetime_20220311.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.keepthetime_20220311.R
import com.example.keepthetime_20220311.databinding.FragmentAppointmentListBinding
import com.example.keepthetime_20220311.datas.BasicResponse
import com.example.keepthetime_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppointmentListFragment : BaseFragment() {

    lateinit var binding : FragmentAppointmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointment_list, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        binding.txtTest.text = "코틀린에서 문구 바꾸기"

    }
}