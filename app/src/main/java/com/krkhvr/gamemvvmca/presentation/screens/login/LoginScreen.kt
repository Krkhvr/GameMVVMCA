package com.krkhvr.gamemvvmca.presentation.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.krkhvr.gamemvvmca.presentation.screens.login.components.LoginFlow
import com.krkhvr.gamemvvmca.presentation.screens.login.components.LoginBottomBar
import com.krkhvr.gamemvvmca.presentation.screens.login.components.LoginContent
import com.krkhvr.gamemvvmca.presentation.ui.theme.GameMVVMCATheme

@Composable
fun LoginScreen(navController: NavHostController) {

    Scaffold(
        topBar = { },
        content = {
            LoginContent(it)
        },
        bottomBar = {
            LoginBottomBar(navController)
        }
    )
    LoginFlow(navHostController = navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    GameMVVMCATheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(rememberNavController())
        }
    }
}