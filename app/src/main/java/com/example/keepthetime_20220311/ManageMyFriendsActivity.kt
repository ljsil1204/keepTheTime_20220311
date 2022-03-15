package com.example.keepthetime_20220311

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keepthetime_20220311.adapters.FriendViewPagerAdapter
import com.example.keepthetime_20220311.adapters.MyFriendAdapter
import com.example.keepthetime_20220311.databinding.ActivityManageMyFriendsBinding
import com.example.keepthetime_20220311.datas.BasicResponse
import com.example.keepthetime_20220311.datas.UserData
import com.example.keepthetime_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageMyFriendsActivity : BaseActivity() {

    lateinit var binding : ActivityManageMyFriendsBinding

    lateinit var mAdapter : FriendViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_my_friends)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnAddFriend.setOnClickListener {
            val myIntent = Intent(mContext, SearchUserActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {

        mAdapter = FriendViewPagerAdapter(supportFragmentManager)
        binding.friendViewPager.adapter = mAdapter

        binding.friendTabLayout.setupWithViewPager(binding.friendViewPager)

    }

}