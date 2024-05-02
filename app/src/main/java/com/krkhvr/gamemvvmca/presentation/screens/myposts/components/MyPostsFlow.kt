package com.krkhvr.gamemvvmca.presentation.screens.myposts.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.presentation.components.DefaultProgressBar
import com.krkhvr.gamemvvmca.presentation.screens.myposts.MyPostsViewModel

@Composable
fun MyPostsFlow(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    viewModel: MyPostsViewModel = hiltViewModel()) {

    when(val response = viewModel.postRespose){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            MyPostsContent(paddingValues, navHostController, response.data)
        }
        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: "Error desconocido",
                Toast.LENGTH_LONG).show()
        }
        else -> { }
    }
}