package com.example.compose.navigationbar.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun MessageScreen(){
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        //页面内容
        Text(text = "Message test", fontSize = 20.sp)
    }
}