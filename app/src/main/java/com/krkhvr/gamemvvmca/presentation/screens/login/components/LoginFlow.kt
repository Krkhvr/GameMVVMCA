package com.krkhvr.gamemvvmca.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.presentation.components.DefaultProgressBar
import com.krkhvr.gamemvvmca.presentation.navigation.Graph
import com.krkhvr.gamemvvmca.presentation.screens.login.LoginViewModel

@Composable
fun LoginFlow(navHostController: NavHostController, loginViewModel: LoginViewModel = hiltViewModel()){
    when(val loginResponse = loginViewModel.loginResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                navHostController.navigate(route = Graph.HOME){
                    popUpTo(Graph.AUTHENTICATION){
                        inclusive = true
                    }
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                loginResponse.exception?.message ?: "Error desconocido",
                Toast.LENGTH_LONG).show()
        }
        else -> { }
    }
}