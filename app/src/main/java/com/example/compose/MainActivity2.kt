package com.example.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.compose.navigationbar.bottomnav.BottomNav
import com.example.compose.ui.theme.ComposeTheme

class MainActivity2: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
               BottomNav()
            }
        }
    }
}