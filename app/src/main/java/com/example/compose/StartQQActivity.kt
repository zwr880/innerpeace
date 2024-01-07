package com.example.compose

import android.content.Intent
import android.os.Bundle
import com.example.compose.QQLogin.BaseUiListener
import com.example.compose.QQLogin.QQAuthActivity
import com.example.compose.QQLogin.showToast
import com.tencent.connect.common.Constants
import com.tencent.tauth.Tencent

class StartQQActivity:BaseActivity() {
    private lateinit var iu: BaseUiListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iu= BaseUiListener(QQAuthActivity.mTencent)
        Tencent.resetTargetAppInfoCache()
        Tencent.resetQQAppInfoCache()
        Tencent.resetTimAppInfoCache()
        if(!QQAuthActivity.mTencent.isSessionValid){
            when(QQAuthActivity.mTencent.login(this,"all",iu)){
                0 -> "正常登录".showToast()
                1 -> "开始登录".showToast()
                -1 -> {
                    "异常".showToast()
                    QQAuthActivity.mTencent.logout(QQAuthActivity.context)
                }
                2 -> "使用H5登陆或显示下载页面".showToast()
                else -> "出错".showToast()
            }
        }
        else{
            "失败".showToast()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCollector.finishAll()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Tencent.onActivityResultData(requestCode,resultCode,data,iu)
        if(requestCode== Constants.REQUEST_API){
            if(resultCode==Constants.REQUEST_LOGIN){
                Tencent.handleResultData(data,iu)
            }
        }
    }
}
