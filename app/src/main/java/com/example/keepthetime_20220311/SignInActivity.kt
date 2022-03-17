package com.example.keepthetime_20220311

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20220311.api.APIList
import com.example.keepthetime_20220311.api.ServerAPI
import com.example.keepthetime_20220311.databinding.ActivitySignInBinding
import com.example.keepthetime_20220311.datas.BasicResponse
import com.example.keepthetime_20220311.utils.ContextUtil
import com.kakao.sdk.user.UserApiClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : BaseActivity() {

    lateinit var binding : ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnKakaoLogin.setOnClickListener {

//            카톡 로그인 기능 실행

//            카톡 앱 로그인이 가능한지?
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)){

//                카톡앱이 설치되어있는 상황
                UserApiClient.instance.loginWithKakaoTalk(mContext) {token, error ->

                    Log.d("카카오로그인", "카톡 앱으로 로그인")
                    getKakaoUserInfo()

                }
            }
            else{

//                카톡앱이 없는 상황, 로그인 창 띄어주기

                UserApiClient.instance.loginWithKakaoAccount(mContext){token, error ->

                    Log.d("카카오로그인", "카톡 앱 없이 로그인")
                    Log.d("카카오로그인", "받아온 토큰 : ${token.toString()}")
                    getKakaoUserInfo()

                }


            }

        }

        binding.btnSignUp.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }


        binding.btnLogin.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()

            apiList.postRequestLogin(inputEmail, inputPassword).enqueue( object  : Callback<BasicResponse>{
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                    Log.d("응답확인", response.toString())

//                    Retrofit 라이브러리의 response는, 성공/실패 여부에 따라 다른 본문을 봐야함.

//                    성공인지?
                    if (response.isSuccessful){
//                        모든 결과가 최종 성공인 경우. (code = 200 으로 내려옴)
//                        response.body() 이용

                        val br = response.body()!! // 성공시 무조건 본문이 있다. => BasicResponse형태의 변수로 파싱되어 나옴.

//                        Retrofit의 Callback은 UITread 안으로 다시 돌아오도록 처리되어있다.
//                        UI 조작을 위해 runOnUiThread{}작성 필요 x
                        Toast.makeText(mContext, "${br.data.user.nick_name}님, 환영합니다!", Toast.LENGTH_SHORT).show()


//                        서버가 내려주는 토큰값을 저장.
                        ContextUtil.setLoginUserToken(mContext, br.data.token)


//                        메인화면으로 이동, 현재 화면 종료

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)

                        finish()

                    }
//                    실패일때는?
                    else{
//                        서버 연경은 되었는데, 로직만 실패 (로그인 비번 틀림)
//                        response.erroBody() 활용.
                    }


                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//                     서버에 물리적 연결 실패
                }

            })

        }


    }

    override fun setValues() {


    }
    
    fun getKakaoUserInfo() {
        
        UserApiClient.instance.me { user, error -> 
            
            user?.let {

//                내정보가 불러와지면, 우리 앱 서버에 소셜 로그인 정보 전달 => 토큰 발급
                apiList.postRequestSocialLogin(
                    "kakao",
                    it.id!!.toString(),
                    it.kakaoAccount!!.profile!!.nickname!!
                ).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        
                        if (response.isSuccessful){
                            
                            val br = response.body()!!
                            
                            ContextUtil.setLoginUserToken(mContext, br.data.token)
                            
                            val myIntent = Intent(mContext, MainActivity::class.java)
                            startActivity(myIntent)

                            Toast.makeText(mContext, "${br.data.user.nick_name}님, 카톡 로그인을 환영합니다!", Toast.LENGTH_SHORT).show()
                            
                        }
                        
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                        
                    }

                })

            }
            
        }
        
    }
    
}