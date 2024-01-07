package com.example.compose

import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.example.compose.QQLogin.BaseUiListener
import com.example.compose.QQLogin.QQAuthActivity
import com.example.compose.QQLogin.QQLogin
import com.example.compose.QQLogin.showToast
import com.google.gson.Gson
import com.tencent.connect.common.Constants
import com.tencent.mmkv.MMKV
import com.tencent.tauth.DefaultUiListener
import com.tencent.tauth.Tencent
import com.tencent.tauth.UiError
import org.json.JSONObject

class BootApplication: BaseActivity() {
    private val kv=MMKV.defaultMMKV()
    private lateinit var iu: BaseUiListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boot_application)
        "更换".showToast()
        //初始化配置
        Tencent.setIsPermissionGranted(true, Build.MODEL)
        Tencent.resetTargetAppInfoCache()
        Tencent.resetQQAppInfoCache()
        Tencent.resetTimAppInfoCache()
        iu= BaseUiListener(QQAuthActivity.mTencent)
        kv.decodeString("qq_login")?.let {
            val gson = Gson()
            val qqLogin = gson.fromJson(it, QQLogin::class.java)
            QQAuthActivity.mTencent.setAccessToken(qqLogin.access_token,qqLogin.expires_in.toString())
            QQAuthActivity.mTencent.openId=qqLogin.openid
        }
        QQAuthActivity.mTencent.checkLogin(object :DefaultUiListener(){
            override fun onComplete(response: Any?) {
                val jsonResp = response as JSONObject
                if(jsonResp.optInt("ret",-1)==0){
                    val jsonObject: String? = kv.decodeString("qq_login")
                    if (jsonObject == null) {
                        "登录失败".showToast()
                    }
                    else {//登陆成功
                      val intent = Intent(this@BootApplication, MainActivity2::class.java)
                        startActivity(intent)
                    }
                }
                else{
                    "登录已过期，请重新登录".showToast()
                    val intent = Intent(this@BootApplication, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            override fun onError(e: UiError) {
                "登录已过期，请重新登录".showToast()
                val intent = Intent(this@BootApplication,MainActivity::class.java)
                startActivity(intent)
            }
            override fun onCancel() {
                "取消登录".showToast()
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //腾讯QQ回调
        Tencent.onActivityResultData(requestCode, resultCode, data,iu)
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, iu)
            }
        }
    }
}