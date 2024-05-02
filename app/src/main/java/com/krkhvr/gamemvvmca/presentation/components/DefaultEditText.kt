package com.krkhvr.gamemvvmca.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.krkhvr.gamemvvmca.presentation.ui.theme.Red700

@Composable
fun DefaultEdiText(
    modifier: Modifier,
    value: String,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
    errorMsg: String = ""
) {
    Column {
        OutlinedTextField(
            modifier = modifier,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            value = value,
            label = {
                Text(text = label)
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = Color.White
                )
            },
            onValueChange = {
                onValueChange.invoke(it)
            },
            visualTransformation =
            if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
        )

        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = errorMsg,
            fontSize = 11.sp,
            color = Red700
        )
    }
}