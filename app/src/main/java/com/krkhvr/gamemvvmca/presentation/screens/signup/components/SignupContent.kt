package com.krkhvr.gamemvvmca.presentation.screens.signup.components

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
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
import com.krkhvr.gamemvvmca.presentation.screens.signup.SignupViewModel
import com.krkhvr.gamemvvmca.presentation.ui.theme.Darkgray500
import com.krkhvr.gamemvvmca.presentation.ui.theme.Red500

@Composable
fun SignupContent(
    paddingValues: PaddingValues,
    signupViewModel: SignupViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .height(220.dp)
                .background(Red500)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    modifier = Modifier.height(120.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = ""
                )
            }

        }

        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 160.dp),
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
                    text = "REGISTRO"
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Por favor ingresa estos datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                DefaultEdiText(
                    modifier = Modifier.padding(top = 10.dp),
                    value = signupViewModel.state.username,
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    onValueChange = {
                        signupViewModel.onUsernameInput(it)
                        signupViewModel.validUsername()
                    },
                    errorMsg = signupViewModel.usernameErrorMsg
                )

                DefaultEdiText(
                    modifier = Modifier.padding(top = 5.dp),
                    value = signupViewModel.state.email,
                    label = "Correo electrónico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    onValueChange = {
                        signupViewModel.onEmailInput(it)
                        signupViewModel.validateEmail()
                    },
                    errorMsg = signupViewModel.emailErrorMsg
                )

                DefaultEdiText(
                    modifier = Modifier.padding(top = 5.dp),
                    value = signupViewModel.state.password,
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password,
                    onValueChange = {
                        signupViewModel.onPasswordInput(it)
                        signupViewModel.validatePassword()
                    },
                    errorMsg = signupViewModel.passwordErrorMsg
                )

                DefaultEdiText(
                    modifier = Modifier.padding(top = 5.dp),
                    value = signupViewModel.state.confirmPassword,
                    label = "Confirmar contraseña",
                    icon = Icons.Outlined.Lock,
                    keyboardType = KeyboardType.Password,
                    onValueChange = {
                        signupViewModel.onConfirmPasswordInput(it)
                        signupViewModel.validateConfirmPassword()
                    },
                    errorMsg = signupViewModel.confirmPasswordErrorMsg
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 5.dp),
                    text = "Registrarse",
                    color = Red500,
                    icon = Icons.Default.ArrowForward,
                    enable = signupViewModel.isAccountValid,
                    onClick = {
                        signupViewModel.signup()
                    }
                )
            }
        }
    }
}