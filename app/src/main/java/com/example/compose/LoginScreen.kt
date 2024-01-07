package com.example.compose

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

//import com.example.compose.QQLogin.StartQQActivity

@Composable
fun LoginScreen(){
    Surface {
        Column(modifier = Modifier.fillMaxSize()){
            TopSection()
            Spacer(modifier = Modifier.height(36.dp))

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)){

                LoginSection()
                Spacer(modifier = Modifier.height(30.dp))
                SocailMediaSection()
            }
        }
    }

}

@Composable
private fun SocailMediaSection() {
    val context = LocalContext.current

    // 用于控制对话框的显示状态
    var isDialogVisible by remember { mutableStateOf(false) }

    val startQQActivityLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // 处理启动Activity的结果
        if (result.resultCode == Activity.RESULT_OK) {
            // 这里可以处理启动Activity成功后的逻辑
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Or continue with",
            style = MaterialTheme.typography.headlineSmall.copy(color = Color(0xFF647488))
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialMediaLogin(
            icon = R.drawable.weixin,
            text = "WeiXin",
            modifier = Modifier.weight(1f)
        ) {
        }
        Spacer(modifier = Modifier.width(20.dp))
        SocialMediaLogin(
            icon = R.drawable.qq,
            text = "QQ",
            modifier = Modifier.weight(1f)
        ) {
            isDialogVisible=true
        }
    }
    if(isDialogVisible){
        AlertDialog(
            onDismissRequest = {
                isDialogVisible=false
            },
            title = {
                Text("Icon Click Check")
            },
            text = {
                Text("Do you want to proceed with the action?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        isDialogVisible=false
                        // 启动StartQQActivity
                        val intent = Intent(context, StartQQActivity::class.java)
                        // 使用ActivityResultLauncher启动Activity
                        startQQActivityLauncher.launch(intent)

                    }) {
                    Text("Proceed")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // 关闭对话框
                        isDialogVisible = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}


@Composable
private fun LoginSection() {
    LoginTextField(label = "Phone", trailing = "", modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.width(15.dp))
    LoginTextField(label = "Password", trailing = "Forget?", modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(20.dp))
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
        )
    ) {
        Text(
            text = "Log in",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
private fun TopSection() {
    val viColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.46f),
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.logo),
                tint = viColor
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.InnerPeace),
                    style = MaterialTheme.typography.headlineLarge,
                    color = viColor
                )
                Text(
                    text = stringResource(id = R.string.HeartHouse),
                    style = MaterialTheme.typography.headlineMedium,
                    color = viColor
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(alignment = Alignment.BottomCenter),
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.titleLarge,
            color = viColor
        )
    }
}

