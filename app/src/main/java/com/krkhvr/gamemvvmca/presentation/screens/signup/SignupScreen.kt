package com.krkhvr.gamemvvmca.presentation.screens.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.krkhvr.gamemvvmca.presentation.components.DefaultTopBar
import com.krkhvr.gamemvvmca.presentation.screens.signup.components.SignupContent
import com.krkhvr.gamemvvmca.presentation.screens.signup.components.SignupFlow
import com.krkhvr.gamemvvmca.presentation.ui.theme.GameMVVMCATheme

@Composable
fun SignupScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo usuario",
                navHostController = navHostController
            )
        },
        content = {
              SignupContent(it)
        },
        bottomBar = {}
    )
    SignupFlow(navHostController = navHostController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignupScreen() {
    GameMVVMCATheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SignupScreen(rememberNavController())
        }
    }
}