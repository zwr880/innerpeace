package com.example.compose.QQLogin

import android.widget.Toast

//对于Toast的一个简单封装，也用到了扩展函数
fun String.showToast(duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(QQAuthActivity.context,this,duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(QQAuthActivity.context,this,duration).show()
}