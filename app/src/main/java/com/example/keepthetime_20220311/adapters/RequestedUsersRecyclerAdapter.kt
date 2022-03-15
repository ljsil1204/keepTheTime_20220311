package com.example.keepthetime_20220311.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keepthetime_20220311.R
import com.example.keepthetime_20220311.datas.UserData

class RequestedUsersRecyclerAdapter(
    val mContext : Context,
    val mList : List<UserData>
) : RecyclerView.Adapter<RequestedUsersRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = view.findViewById<TextView>(R.id.txtNickname)
        val imgSocialLoginLogo = view.findViewById<ImageView>(R.id.imgSocialLoginLogo)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)

        fun bind(data: UserData) {

            Glide.with(mContext).load(data.profile_img).into(imgProfile)
            txtNickname.text = data.nick_name

            when(data.provider){
                "default" -> {
                    //                이메일 표시
                    txtEmail.text = data.email

                    //                로고 이미지 숨김
                    imgSocialLoginLogo.visibility = View.GONE
                }
                "kakao" -> {
                    //                "카카오 로그인"
                    txtEmail.text = "카카오로그인"

                    //                로고이미지 : 카카오 아이콘
                    imgSocialLoginLogo.visibility = View.VISIBLE
                    Glide.with(mContext).load(R.drawable.sns_kakao).into(imgSocialLoginLogo)
                }
                "facebook" ->{
                    txtEmail.text = "페북 로그인"
                    Glide.with(mContext).load(R.drawable.sns_facebook).into(imgSocialLoginLogo)
                }
                "naver" -> {
                    txtEmail.text = "네이버 로그인"

                    //                Glide는 웹의 이미지 뿐만 아니라, 우리 프로젝트 내부의 이미지도 불러낼 수 있다.
                    Glide.with(mContext).load(R.drawable.sns_naver).into(imgSocialLoginLogo)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.requested_user_list_item, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = mList[position]
        holder.bind(data)

    }

    override fun getItemCount() = mList.size

}