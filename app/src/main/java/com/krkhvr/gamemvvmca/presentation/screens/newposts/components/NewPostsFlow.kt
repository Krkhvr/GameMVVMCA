package com.krkhvr.gamemvvmca.presentation.screens.newposts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.presentation.components.DefaultProgressBar
import com.krkhvr.gamemvvmca.presentation.screens.newposts.NewPostViewModel

@Composable
fun NewPostsFlow(newPostViewModel: NewPostViewModel = hiltViewModel()){

    val context = LocalContext.current

    when(val response = newPostViewModel.createPostResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                newPostViewModel.clearScreen()
                Toast.makeText(
                    context, "La publicación se ha creado correctamente", Toast.LENGTH_LONG).show()
            }
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