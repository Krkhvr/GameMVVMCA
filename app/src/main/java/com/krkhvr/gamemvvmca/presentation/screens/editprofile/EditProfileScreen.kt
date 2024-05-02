package com.krkhvr.gamemvvmca.presentation.screens.editprofile

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.krkhvr.gamemvvmca.presentation.components.DefaultTopBar
import com.krkhvr.gamemvvmca.presentation.screens.editprofile.components.EditProfileContent
import com.krkhvr.gamemvvmca.presentation.screens.editprofile.components.EditProfileFlow

@Composable
fun EditProfileScreen(navHostController: NavHostController, user: String){
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar usuario",
                navHostController = navHostController
            )
        },
        content = {
            EditProfileContent(it)
        },
        bottomBar = {}
    )
    //SaveImageFlow()
    EditProfileFlow()
}