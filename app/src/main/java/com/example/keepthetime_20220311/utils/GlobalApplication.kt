package com.example.keepthetime_20220311.utils

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "57936e64c720ee7488288b2a7325e9f2")
    }

}