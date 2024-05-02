package com.krkhvr.gamemvvmca.presentation.screens.editprofile.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.krkhvr.gamemvvmca.domain.model.Response
import com.krkhvr.gamemvvmca.presentation.components.DefaultProgressBar
import com.krkhvr.gamemvvmca.presentation.screens.editprofile.EditProfileViewModel

@Composable
fun SaveImageFlow(
    editProfileViewModel: EditProfileViewModel = hiltViewModel()
) {
    when(val updateResponse = editProfileViewModel.saveImageResponse){
        Response.Loading -> {
            DefaultProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                editProfileViewModel.onImageInput(updateResponse.data)
                editProfileViewModel.updateUsername()
            }
        }

        is Response.Failure -> {
            Toast.makeText(LocalContext.current, updateResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}