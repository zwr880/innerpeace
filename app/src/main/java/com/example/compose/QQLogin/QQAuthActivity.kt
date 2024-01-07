package com.example.compose.QQLogin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import com.example.compose.R
import com.tencent.mmkv.MMKV
import com.tencent.tauth.Tencent

class QQAuthActivity :Application() {
    companion object{
        /**
         * 用于全局获取上下文
         */
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var  mTencent: Tencent
    }

    override fun onCreate() {
        super.onCreate()
        Tencent.setIsPermissionGranted(true, Build.MODEL)
        val appId=getString(R.string.qq_app_id)
        context=applicationContext
        MMKV.initialize(context)
        mTencent=Tencent.createInstance(appId, context,"com.tencent.login.file-provider")
    }
}