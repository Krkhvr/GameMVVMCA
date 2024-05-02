package com.krkhvr.gamemvvmca.presentation.screens.signup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.presentation.components.DefaultProgressBar
import com.krkhvr.gamemvvmca.presentation.navigation.Graph
import com.krkhvr.gamemvvmca.presentation.screens.signup.SignupViewModel

@Composable
fun SignupFlow(navHostController: NavHostController, signupViewModel: SignupViewModel = hiltViewModel()) {
    signupViewModel.signupFlow.collectAsState().value.let {
        when(it){
            Response.Loading -> {
                DefaultProgressBar()
            }
            is Response.Success -> {
                LaunchedEffect(Unit){
                    signupViewModel.createUser()
                    navHostController.navigate(route = Graph.AUTHENTICATION){
                        popUpTo(Graph.HOME){
                            inclusive = true
                        }
                    }
                }
            }
            is Response.Failure -> {
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }
            else -> { }
        }
    }

}