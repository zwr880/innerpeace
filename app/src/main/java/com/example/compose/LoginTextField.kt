package com.example.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.compose.ui.theme.Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    label: String,
    trailing: String
){
    val viColor = if(isSystemInDarkTheme()) Color.White else Black

    TextField(
        modifier = modifier,
        value = "",
        onValueChange = {},
        label ={
            Text(text = label,style = MaterialTheme.typography.headlineSmall,color=viColor)

            },
        colors = TextFieldDefaults.textFieldColors(),
        trailingIcon = {
            Text(
                text = trailing,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Medium),
                color = viColor
                )
        }
    )
}