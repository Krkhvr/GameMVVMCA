package com.krkhvr.gamemvvmca.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.krkhvr.gamemvvmca.presentation.ui.theme.Red700

@Composable
fun DefaultButton(
    modifier: Modifier,
    text: String,
    errorMsg: String = "",
    color: Color,
    icon: ImageVector,
    enable: Boolean = true,
    onClick: () -> Unit,
){
    Column {
        Button(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(containerColor = color),
            onClick = {
                onClick.invoke()
            },
            enabled = enable
        ) {
            Icon(imageVector = icon, contentDescription = "")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text)
        }

        /*Text(
            modifier = Modifier.padding(top = 5.dp),
            fontSize = 11.sp,
            color = Red700,
            text = errorMsg
        )*/
    }

}