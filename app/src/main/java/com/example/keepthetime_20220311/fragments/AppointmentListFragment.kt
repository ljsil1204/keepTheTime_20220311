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

    lateinit var mAdapter : AppointmentListRecyclerAdapter

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

        mAdapter = AppointmentListRecyclerAdapter(mContext, mAppintmentList)
        binding.appointmentRecyclerView.adapter = mAdapter
        binding.appointmentRecyclerView.layoutManager = LinearLayoutManager(mContext)

        getMyAppointmentListFromServer()

    }

    fun getMyAppointmentListFromServer() {

        apiList.getRequestAppointmentList().enqueue( object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful){

                    val br = response.body()!!

                    mAppintmentList.clear()

                    mAppintmentList.addAll(br.data.appointments)

                    mAdapter.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }

}