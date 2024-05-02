package com.krkhvr.gamemvvmca.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.krkhvr.gamemvvmca.R
import com.krkhvr.gamemvvmca.presentation.components.DefaultButton
import com.krkhvr.gamemvvmca.presentation.components.DefaultEdiText
import com.krkhvr.gamemvvmca.presentation.screens.login.LoginViewModel
import com.krkhvr.gamemvvmca.presentation.ui.theme.Darkgray500
import com.krkhvr.gamemvvmca.presentation.ui.theme.Red500

@Composable
fun LoginContent(
    paddingValues: PaddingValues,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .height(280.dp)
                .background(Red500)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier.height(130.dp),
                    painter = painterResource(id = R.drawable.control),
                    contentDescription = "Xbox 360 control"
                )
                Text(
                    text = "Firebase MVVM"
                )
            }

        }

        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = Darkgray500 //Card background color
                //contentColor = Color.White  //Card content color,e.g.text
            )
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 30.dp)
            ) {
                Text(
                    modifier =
                    Modifier.padding(top = 40.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    text = "LOGIN"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor inicia sesión para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                DefaultEdiText(
                    modifier = Modifier.padding(top = 25.dp),
                    value = loginViewModel.state.email,
                    label = "Correo electrónico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    onValueChange = {
                        loginViewModel.onEmailInput(it)
                        loginViewModel.validateEmail()
                    },
                    errorMsg = loginViewModel.emailErrorMsg
                )

                DefaultEdiText(
                    modifier = Modifier.padding(top = 10.dp),
                    value = loginViewModel.state.password,
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password,
                    onValueChange = {
                        loginViewModel.onPasswordInput(it)
                        loginViewModel.validatePassword()
                    },
                    errorMsg = loginViewModel.passwordErrorMsg
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 50.dp),
                    text = "Iniciar sesión",
                    color = Red500,
                    icon = Icons.Default.ArrowForward,
                    enable = loginViewModel.isAccountValid,
                    onClick = {
                        loginViewModel.login()
                    }
                )
            }
        }
    }
}