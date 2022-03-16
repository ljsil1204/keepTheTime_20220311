package com.example.keepthetime_20220311.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keepthetime_20220311.EditAppointmentActivity
import com.example.keepthetime_20220311.R
import com.example.keepthetime_20220311.adapters.AppointmentListRecyclerAdapter
import com.example.keepthetime_20220311.databinding.FragmentAppointmentListBinding
import com.example.keepthetime_20220311.datas.AppointmentData
import com.example.keepthetime_20220311.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppointmentListFragment : BaseFragment() {

    lateinit var binding : FragmentAppointmentListBinding

    val mAppintmentList = ArrayList<AppointmentData>()

    lateinit var mAppointmentAdapter : AppointmentListRecyclerAdapter

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

        binding.btnAppointment.setOnClickListener {

            val myIntent = Intent(mContext, EditAppointmentActivity::class.java)
            startActivity(myIntent)

        }

    }

    override fun setValues() {

        mAppointmentAdapter = AppointmentListRecyclerAdapter(mContext, mAppintmentList)
        binding.appointmentRecyclerView.adapter = mAppointmentAdapter
        binding.appointmentRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }

    override fun onResume() {
        super.onResume()
        getMyAppointmentListFromServer()
    }

    fun getMyAppointmentListFromServer() {

        apiList.getRequestAppointmentList().enqueue( object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful){

//                    기존의 약속목록을 비우고 나서 추가
                    mAppintmentList.clear()

                    val br = response.body()!!

                    mAppintmentList.addAll(br.data.appointments)

                    mAppointmentAdapter.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }



}